package com.example.hackathon.service;

import com.example.hackathon.dto.*;
import com.example.hackathon.exception.EventNotFoundException;
import com.example.hackathon.model.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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
    public GameResponseDto initializeGame(GameRequestDto gameRequestDto) {
        //leaderService --- leaderId -- state event

        return this.gameService.initializeGame(gameRequestDto);
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

    private PlayerDto playerModelToDto( Player playerModel){
        PlayerDto playerDto = new PlayerDto();
        playerDto.setPlayerName(playerModel.getPlayerName());
        playerDto.setPlayerId(playerModel.getId());
        return playerDto;
    }

    private Player playerDtoToModel(PlayerDto newPlayerDto){
        Player newPlayerModel = new Player();
        newPlayerModel.setPlayerName(newPlayerDto.getPlayerName());
        newPlayerModel.setId(newPlayerDto.getPlayerId());
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
        StatesDto statesDto = createStatesDto(event.getReportState());
        eventDto.setReportStatesDto(statesDto);
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
