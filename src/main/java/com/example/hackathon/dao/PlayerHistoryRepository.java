package com.example.hackathon.dao;

import com.example.hackathon.model.Leader;
import com.example.hackathon.model.Player;
import com.example.hackathon.model.PlayerHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerHistoryRepository extends CrudRepository<PlayerHistory,Long>  {

    List<PlayerHistory> findByPlayerId(long playerId );
    List<PlayerHistory> findByPlayerIdAndCurrentGameInstanceId(long playerId );
}
