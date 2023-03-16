package com.example.hackathon.controller;

import com.example.hackathon.dto.*;
import com.example.hackathon.model.Game;
import com.example.hackathon.service.BoardManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/game")
public class GameController {


    private final BoardManager boardManager;

    public GameController(BoardManager boardManager){
        this.boardManager = boardManager;
    }



    @GetMapping(value = "/next-move")
    public NextMoveResponseDto nextMove(NextMoveDto nextMoveDto){
        return  this.nextMove(nextMoveDto);
    }



    @GetMapping(value = "/getGames")
    public List<Game> getGames(){
        return this.boardManager.getAllGames();
    }


    @PostMapping(value="/join")
    public GameResponseDto joinGame(JoinRequestDto joinRequestDto){
        return this.boardManager.joinGame(joinRequestDto);
    }

    @PostMapping(value="/add-player")
    public  PlayerDto addPlayer(PlayerDto playerDto){
        return this.boardManager.addPlayer(playerDto);
    }
}
