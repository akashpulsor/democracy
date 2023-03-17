package com.example.hackathon.service;

import com.example.hackathon.dto.*;
import com.example.hackathon.exception.EventNotFoundException;
import com.example.hackathon.model.*;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BoardManagerImpl implements BoardManager{

    // board
    private final GameService gameService;

    private final LeaderService leaderService;

    private  final PlayerService playerService;

    public BoardManagerImpl(GameService gameService,LeaderService leaderService,
                            PlayerService playerService){
        this.gameService = gameService;
        this.leaderService = leaderService;
        this.playerService = playerService;
    }




    @Override
    public NextMoveResponseDto NextMove(NextMoveDto nextMoveDto) {

        // true --- 4 -- event ---4 -3
        // 4
        // false -- states ---  progpaganda
        // event --
        // states
        // events
        return this.NextMove(nextMoveDto);
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
        return gameResponseDto;
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
        event.setPropagandaFreeMediaMap(eventDto.getPropagandaFreeMediaMap());
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
        eventDto.setPropagandaFreeMediaMap(event.getPropagandaFreeMediaMap());
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
