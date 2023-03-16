package com.example.hackathon.service;

import com.example.hackathon.model.Game;
import com.example.hackathon.model.Leader;
import org.springframework.data.repository.Repository;

public interface LeaderRepository extends Repository<Leader, Long> {

    Leader findByLeaderId( long gameId );
}
