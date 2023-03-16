package com.example.hackathon.service;

import com.example.hackathon.model.Game;
import com.example.hackathon.model.Player;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface GameRepository extends Repository<Game, Long> {

    Game save( Game game );
    Game findByGameId( long gameId );


    List<Game> findAll();
}
