package com.example.hackathon.service;

import com.example.hackathon.dao.PlayerRepository;
import com.example.hackathon.model.Player;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    private PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }


    public Player addPlayer(Player player){
        return this.playerRepository.save(player);
    }
}
