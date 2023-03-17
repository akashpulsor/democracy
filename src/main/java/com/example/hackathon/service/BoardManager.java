package com.example.hackathon.service;

import com.example.hackathon.dto.*;
import com.example.hackathon.model.*;

import java.util.List;
import java.util.Set;

public interface BoardManager {


    NextMoveResponseDto nextMove(NextMoveDto nextMoveDto);

    Event nextMove1(NextMoveDto nextMoveDto);

    List<Game> getAllGames();

    GameResponseDto joinGame(JoinRequestDto joinRequestDto);

    PlayerDto addPlayer(PlayerDto playerDto);

    Leader addLeader(LeaderDto leaderDto);

    Leader getLeader(LeaderDto leaderDto);

    Set<Event> getEventByLeader(LeaderDto leaderDto);

    PlayerHistory addWatchHistory(PlayerHistory playerHistory);

}
