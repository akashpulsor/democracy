package com.example.hackathon.service;

import com.example.hackathon.dto.GameRequestDto;
import com.example.hackathon.dto.GameResponseDto;
import com.example.hackathon.dto.NextMoveDto;
import com.example.hackathon.dto.NextMoveResponseDto;

public interface BoardManager {
    GameResponseDto initializeGame(GameRequestDto gameRequestDto);

    NextMoveResponseDto NextMove(NextMoveDto nextMoveDto);
}
