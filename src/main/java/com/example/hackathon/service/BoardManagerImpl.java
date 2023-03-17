package com.example.hackathon.service;

import com.example.hackathon.dto.*;
import com.example.hackathon.exception.EventNotFoundException;
import com.example.hackathon.model.*;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class BoardManagerImpl implements BoardManager{

    // board
    private final GameService gameService;

    private final LeaderService leaderService;

    private  final PlayerService playerService;

    private final EventsService eventsService;

    public BoardManagerImpl(GameService gameService,LeaderService leaderService,
                            PlayerService playerService, EventsService eventsService){
        this.gameService = gameService;
        this.leaderService = leaderService;
        this.playerService = playerService;
        this.eventsService = eventsService;
    }



    //TODO corner case of next event will be dealt later
    //TODO polarization on the basis of free media will be dealt later
    @Override
    public NextMoveResponseDto nextMove(NextMoveDto nextMoveDto) {
        Event event =this.eventsService.getEventById(nextMoveDto.getEventId());
        List<PlayerHistory> playerHistory = this.playerService.getPlayerHistory(nextMoveDto.getPlayerId(),
                nextMoveDto.getCurrentGameInstanceId());
        Set<Event> events = this.leaderService.getEventByLeader(nextMoveDto.getLeaderId());
        CurrentGameInstance currentGameInstance = this.gameService.
                getCurrentGameInstanceId(nextMoveDto.getCurrentGameInstanceId());
        List<State> notWatched = notWatchedVideo(event,playerHistory);
        Set<Long> watchedEvents = playerHistory.stream()
                .map(PlayerHistory::getWatchedEvent).collect(Collectors.toSet());

        return this.nextMove(nextMoveDto);
    }

    @Override
    public Event nextMove1(NextMoveDto nextMoveDto) {
        Event event =this.eventsService.getEventById(nextMoveDto.getEventId());
        List<PlayerHistory> playerHistory = this.playerService.getPlayerHistory(nextMoveDto.getPlayerId(),
                nextMoveDto.getCurrentGameInstanceId());
        Set<Event> events = this.leaderService.getEventByLeader(nextMoveDto.getLeaderId());
        CurrentGameInstance currentGameInstance = this.gameService.
                getCurrentGameInstanceId(nextMoveDto.getCurrentGameInstanceId());
        Set<Long> watchedEvents = playerHistory.stream()
                .map(PlayerHistory::getWatchedEvent).collect(Collectors.toSet());
        List<Event> notProcessedEventsList=notProcessedEvents(events,watchedEvents);

        Optional<Event> temp = notProcessedEventsList.stream().findAny();
        if(notProcessedEventsList.isEmpty()){
            return new Event();
        }
        return temp.get();
    }

    private List<State> notWatchedVideo(Event event, List<PlayerHistory> playerHistoryList){
        Set<Long> watchedVideos = playerHistoryList.stream()
                .map(PlayerHistory::getWatchedState).collect(Collectors.toSet());


        List<State> notWatched = new ArrayList<>();
        for(State state: event.getMediaList()){
            if(watchedVideos.contains(state.getId())){
                notWatched.add(state);
            }
        }
        return notWatched;
    }


    private List<Event> notProcessedEvents(Set<Event> events,Set<Long> watchedEvents){
        List<Event> notProcessedEvents = new ArrayList<>();
        for (Event event:
        events) {
            if(!watchedEvents.contains(event.getId())){
                notProcessedEvents.contains(event);
            }
        }
        return notProcessedEvents;
    }




    @Override
    public List<Game> getAllGames() {
        return this.gameService.getGameList();
    }

    @Override
    public GameResponseDto joinGame(JoinRequestDto joinRequestDto) {
        Player newPlayerModel = playerDtoToModel(joinRequestDto.getPlayerDto());
        Game game = this.gameService.getGameById(joinRequestDto.getGameId());
        game.getPlayer().add(newPlayerModel);
        game = this.gameService.saveGame(game);
        Leader leaderLeader = this.leaderService.getLeader(game.getLeaderId());
        Optional<Event> eventOptional = leaderLeader.getEvent().stream().findAny();
        if(eventOptional.isEmpty()){
            throw  new EventNotFoundException("Event not found exception");
        }
        Event event = eventOptional.get();
        GameResponseDto gameResponseDto = createGameResponseDto(game);
        EventDto eventsDto = createEventDto(event);
        gameResponseDto.setEventDto(eventsDto);
        CurrentGameInstance currentGameInstance = createCurrentGameInstance(game, newPlayerModel);
        currentGameInstance =this.gameService.getCurrentGameInstance(currentGameInstance);
        gameResponseDto.setCurrentGameInstanceId(currentGameInstance.getId());
        return gameResponseDto;
    }

    private CurrentGameInstance createCurrentGameInstance(Game game, Player Player){
        CurrentGameInstance currentGameInstance = new CurrentGameInstance();
        currentGameInstance.setGameId(game.getGameId());
        currentGameInstance.setId(Player.getId());
        return currentGameInstance;
    }

    @Override
    public PlayerDto addPlayer(PlayerDto playerDto) {
        Player player = playerDtoToModel(playerDto);
        player = this.playerService.addPlayer(player);
        return playerModelToDto(player);
    }

    @Override
    public Leader addLeader(LeaderDto leaderDto) {
        return this.leaderService.addLeader(LeaderDtoToLeader(leaderDto));
    }

    @Override
    public Leader getLeader(LeaderDto leaderDto) {
        return this.leaderService.getLeader(leaderDto.getLeaderId());
    }

    @Override
    public Set<Event> getEventByLeader(LeaderDto leaderDto) {
        return this.leaderService.getEventByLeader(leaderDto.getLeaderId());
    }

    @Override
    public PlayerHistory addWatchHistory(PlayerHistory playerHistory) {
        return this.playerService.addPlayerHistory(playerHistory);
    }

    private Leader LeaderDtoToLeader(LeaderDto leaderDto){
        Leader leader = new Leader();
        leader.setId(leaderDto.getLeaderId());
        Set<Event> events = new HashSet<>();
        for (EventDto eventDto:
        leaderDto.getEventDtoList()) {
            Event event =eventDtoToEvent(eventDto,leader);
            events.add(event);
        }
        leader.setEvent(events);
        return leader;
    }

    private Event eventDtoToEvent(EventDto eventDto, Leader leader){
        Event event = new Event();
        event.setId(eventDto.getEventId());
        event.setLeader(leader);
        event.setMediaList(eventDto.getMediaList());
        event.setEventState(eventDto.getEventState());
        event.setReportState(eventDto.getReportStatesDto());
        return event;
    }

    private PlayerDto playerModelToDto( Player playerModel){
        PlayerDto playerDto = new PlayerDto();
        playerDto.setPlayerName(playerModel.getPlayerName());
        playerDto.setPlayerId(playerModel.getId());
        playerDto.setFreeMediaEnabled(playerModel.isFreeMediaEnabled());
        return playerDto;
    }

    private Player playerDtoToModel(PlayerDto newPlayerDto){
        Player newPlayerModel = new Player();
        newPlayerModel.setPlayerName(newPlayerDto.getPlayerName());
        newPlayerModel.setId(newPlayerDto.getPlayerId());
        newPlayerModel.setFreeMediaEnabled(newPlayerDto.isFreeMediaEnabled());
        return newPlayerModel;
    }

    private GameResponseDto createGameResponseDto(Game game){
        GameResponseDto gameResponseDto = new GameResponseDto();
        gameResponseDto.setGameId(game.getGameId());
        gameResponseDto.setLeaderId(game.getLeaderId());
        return gameResponseDto;
    }

    private EventDto createEventDto(Event event){
        EventDto eventDto = new EventDto();
        eventDto.setEventId(event.getId());
        eventDto.setReportStatesDto(event.getReportState());
        eventDto.setMediaList(event.getMediaList());
        eventDto.setEventState(event.getEventState());
        return eventDto;
    }

    private StatesDto createStatesDto(State state){
        StatesDto statesDto = new StatesDto();
        statesDto.setStateId(state.getId());
        statesDto.setUrl(state.getUrl());
        statesDto.setType(state.getType());
        return statesDto;
    }



}
