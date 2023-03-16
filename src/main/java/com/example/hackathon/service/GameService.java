package com.example.hackathon.service;

import com.example.hackathon.dto.GameRequestDto;
import com.example.hackathon.dto.GameResponseDto;
import com.example.hackathon.dto.NextMoveDto;
import com.example.hackathon.dto.NextMoveResponseDto;
import org.springframework.stereotype.Service;

@Service
public class GameService implements  Game {
    @Override
    public NextMoveResponseDto NextMove(NextMoveDto nextMoveDto) {
        return null;
    }

    @Override
    public GameResponseDto initializeGame(GameRequestDto gameRequestDto) {
        return null;
    }

    @Override
    public NextMoveResponseDto endGame(NextMoveDto nextMoveDto){
        return null;
    }


}
