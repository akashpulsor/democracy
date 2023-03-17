package com.example.hackathon.controller;

import com.example.hackathon.dto.*;
import com.example.hackathon.model.Event;
import com.example.hackathon.model.Game;
import com.example.hackathon.model.Leader;
import com.example.hackathon.service.BoardManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController("/game")
public class GameController {


    private final BoardManager boardManager;

    public GameController(BoardManager boardManager){
        this.boardManager = boardManager;
    }



    @GetMapping(value = "/next-move")
    public NextMoveResponseDto nextMove(NextMoveDto nextMoveDto){
        return  this.boardManager.nextMove(nextMoveDto);
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


    @PostMapping(value="/add-events")
    public Leader addEvents(LeaderDto leaderDto){
        return this.boardManager.addLeader(leaderDto);
    }

    @GetMapping(value="/get-events")
    public Set<Event> getEvents(LeaderDto leaderDto){
        return this.boardManager.getEventByLeader(leaderDto);
    }

    @PostMapping(value="/add-leader")
    public Leader addLeader(LeaderDto leaderDto){
        return this.boardManager.addLeader(leaderDto);
    }


    @PostMapping(value="/getLeader")
    public Leader getLeader(LeaderDto leaderDto){
        return this.boardManager.getLeader(leaderDto);
    }



}
