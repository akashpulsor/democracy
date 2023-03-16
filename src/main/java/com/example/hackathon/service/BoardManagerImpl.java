package com.example.hackathon.service;

import com.example.hackathon.dto.*;
import com.example.hackathon.model.Game;
import com.example.hackathon.model.Player;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BoardManagerImpl implements BoardManager{

    // board
    private final GameService gameService;

    private final LeaderService leaderService;

    public BoardManagerImpl(GameService gameService,LeaderService leaderService){
        this.gameService = gameService;
        this.leaderService = leaderService;
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
    public Game joinGame(JoinRequestDto joinRequestDto) {
        Player newPlayerModel = playerDtoToModel(joinRequestDto.getPlayer());
        Game game = this.gameService.getGameById(joinRequestDto.getGameId());
        game.getPlayer().add(newPlayerModel);
        game = this.gameService.saveGame(game);
        return this.gameService.saveGame(game);
    }

    private Player playerDtoToModel(com.example.hackathon.dto.Player newPlayer){
        Player newPlayerModel = new Player();
        newPlayerModel.setPlayerName(newPlayer.getPlayerName());
        newPlayerModel.setId(newPlayer.getPlayerId());
        return newPlayerModel;
    }


    //
}
