package com.example.hackathon.service;


import com.example.hackathon.model.Event;
import com.example.hackathon.model.Leader;

import java.util.List;
import java.util.Set;

public interface LeaderService {


    Leader getLeader(long leaderId);

    Set<Event> getEventByLeader(long leaderId);

    Leader addLeader(Leader leader);
}
