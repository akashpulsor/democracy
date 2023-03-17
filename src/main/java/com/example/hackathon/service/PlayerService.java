package com.example.hackathon.service;

import com.example.hackathon.dao.PlayerHistoryRepository;
import com.example.hackathon.dao.PlayerRepository;
import com.example.hackathon.model.Player;
import com.example.hackathon.model.PlayerHistory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private PlayerRepository playerRepository;

    private PlayerHistoryRepository playerHistoryRepository;

    public PlayerService(PlayerRepository playerRepository, PlayerHistoryRepository playerHistoryRepository) {
        this.playerRepository = playerRepository;
        this.playerHistoryRepository = playerHistoryRepository;
    }


    public Player addPlayer(Player player){
        return this.playerRepository.save(player);
    }

    public List<PlayerHistory> getPlayerHistory(long playerId, long current_game_instance_id) {
        return this.playerHistoryRepository.findByPlayerIdAndCurrentGameInstanceId(playerId,current_game_instance_id);
    }

    public PlayerHistory addPlayerHistory(PlayerHistory playerHistory){
        return this.playerHistoryRepository.save(playerHistory);
    }
}
