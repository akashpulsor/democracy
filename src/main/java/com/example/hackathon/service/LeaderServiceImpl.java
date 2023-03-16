package com.example.hackathon.service;

import com.example.hackathon.dao.LeaderRepository;
import com.example.hackathon.model.Leader;
import org.springframework.stereotype.Service;

@Service
public class LeaderServiceImpl implements  LeaderService {

    private final LeaderRepository leaderRepository;

    public LeaderServiceImpl(LeaderRepository leaderRepository) {
        this.leaderRepository = leaderRepository;
    }

    @Override
    public Leader getLeader(long leaderId) {
        return this.leaderRepository.findByLeaderId(leaderId);
    }
}
