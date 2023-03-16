package com.example.hackathon.service;

import com.example.hackathon.dto.GameRequestDto;
import com.example.hackathon.dto.GameResponseDto;
import com.example.hackathon.dto.NextMoveDto;
import com.example.hackathon.dto.NextMoveResponseDto;

public interface Game {

    NextMoveResponseDto NextMove(NextMoveDto nextMoveDto);

    GameResponseDto initializeGame(GameRequestDto gameRequestDto);

    NextMoveResponseDto endGame(NextMoveDto nextMoveDto);
}
