package com.example.hackathon.controller;

import com.example.hackathon.dto.GameRequestDto;
import com.example.hackathon.dto.GameResponseDto;
import com.example.hackathon.dto.NextMoveDto;
import com.example.hackathon.dto.NextMoveResponseDto;
import com.example.hackathon.service.BoardManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/game")
public class GameController {


    private final BoardManager boardManager;

    public GameController(BoardManager boardManager){
        this.boardManager = boardManager;
    }
    @GetMapping(value = "/initialize")
    public GameResponseDto initializeGame(GameRequestDto gameRequestDto){
        return this.boardManager.initializeGame(gameRequestDto);
    }


    @GetMapping(value = "/next-move")
    public NextMoveResponseDto nextMove(NextMoveDto nextMoveDto){
        return  this.nextMove(nextMoveDto);
    }
}
