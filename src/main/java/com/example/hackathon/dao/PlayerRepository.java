package com.example.hackathon.dao;

import com.example.hackathon.model.Game;
import com.example.hackathon.model.Leader;
import com.example.hackathon.model.Player;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player,Long>,
        QuerydslPredicateExecutor<Player> {
    Player save(Player player);

    Player findByPlayerId( Integer playerId );
}
