package com.example.hackathon.dao;

import com.example.hackathon.model.Game;
import com.example.hackathon.model.Player;
import com.example.hackathon.model.PropagandaFreeMedia;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository  extends CrudRepository<Game,Long>  {

    Game save( Game game );
    Game findByGameId( long gameId );


    List<Game> findAll();
}
