package com.example.hackathon.service;

import com.example.hackathon.dao.CurrentGameInstanceRepository;
import com.example.hackathon.dao.GameRepository;
import com.example.hackathon.dto.GameRequestDto;
import com.example.hackathon.dto.GameResponseDto;
import com.example.hackathon.dto.NextMoveDto;
import com.example.hackathon.dto.NextMoveResponseDto;
import com.example.hackathon.model.CurrentGameInstance;
import com.example.hackathon.model.Game;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService  {

    private final GameRepository gameRepository;

    private final CurrentGameInstanceRepository currentGameInstanceRepository;

    public GameService(GameRepository gameRepository,
                       CurrentGameInstanceRepository currentGameInstanceRepository){
        this.gameRepository = gameRepository;
        this.currentGameInstanceRepository = currentGameInstanceRepository;
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

    public CurrentGameInstance getCurrentGameInstance(CurrentGameInstance currentGameInstance){
        return this.currentGameInstanceRepository.save(currentGameInstance);
    }

}
