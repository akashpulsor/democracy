package com.example.hackathon.service;

import com.example.hackathon.model.Game;
import com.example.hackathon.model.Player;
import org.springframework.data.repository.Repository;

public interface PlayerRepository extends Repository<Player, Long> {
    Player save(Player player );

    Player findByPlayerId( Integer playerId );
}
