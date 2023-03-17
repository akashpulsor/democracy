package com.example.hackathon.dao;

import com.example.hackathon.model.Event;
import com.example.hackathon.model.Game;
import com.example.hackathon.model.Leader;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface LeaderRepository extends CrudRepository<Leader,Long>,
        QuerydslPredicateExecutor<Leader> {

    Leader findByLeaderId( long leaderId );

    Set<Event> findEventById(long leaderId);

    Leader save(Leader leader);
}
