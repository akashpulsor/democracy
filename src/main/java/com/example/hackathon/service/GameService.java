package com.example.hackathon.service;

import com.example.hackathon.dao.GameRepository;
import com.example.hackathon.dto.GameRequestDto;
import com.example.hackathon.dto.GameResponseDto;
import com.example.hackathon.dto.NextMoveDto;
import com.example.hackathon.dto.NextMoveResponseDto;
import com.example.hackathon.model.Game;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService  {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }
    public NextMoveResponseDto NextMove(NextMoveDto nextMoveDto) {
        return null;
    }


    public GameResponseDto initializeGame(GameRequestDto gameRequestDto) {
        return null;
    }


    public NextMoveResponseDto endGame(NextMoveDto nextMoveDto){
        return null;
    }


    public List<Game> getGameList(){
        return this.gameRepository.findAll();
    }


    public Game getGameById(long gameId){
        return this.gameRepository.findByGameId(gameId);
    }

    public Game saveGame(Game game){
        return this.gameRepository.save(game);
    }


}
