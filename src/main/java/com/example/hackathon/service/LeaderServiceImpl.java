package com.example.hackathon.service;

import com.example.hackathon.dao.LeaderRepository;
import com.example.hackathon.model.Event;
import com.example.hackathon.model.Leader;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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

    @Override
    public Set<Event> getEventByLeader(long leaderId) {
        return this.leaderRepository.findEventById(leaderId);
    }

    @Override
    public Leader addLeader(Leader leader) {
        return this.leaderRepository.save(leader);
    }
}
