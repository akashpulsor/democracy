package com.example.hackathon.service;

import com.example.hackathon.dto.*;
import com.example.hackathon.model.Game;

import java.util.List;

public interface BoardManager {
    GameResponseDto initializeGame(GameRequestDto gameRequestDto);

    NextMoveResponseDto NextMove(NextMoveDto nextMoveDto);

    List<Game> getAllGames();

    Game joinGame(JoinRequestDto joinRequestDto);

}
