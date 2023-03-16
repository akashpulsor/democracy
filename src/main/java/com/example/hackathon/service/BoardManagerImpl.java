package com.example.hackathon.service;

import com.example.hackathon.dto.GameRequestDto;
import com.example.hackathon.dto.GameResponseDto;
import com.example.hackathon.dto.NextMoveDto;
import com.example.hackathon.dto.NextMoveResponseDto;
import org.springframework.stereotype.Component;

@Component
public class BoardManagerImpl implements BoardManager{

    // board
    private final GameService gameService;

    public BoardManagerImpl(GameService gameService){
        this.gameService = gameService;
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


    //
}
