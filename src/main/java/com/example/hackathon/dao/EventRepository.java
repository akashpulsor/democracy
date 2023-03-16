package com.example.hackathon.dao;

import com.example.hackathon.model.Event;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<Event,Long>, QuerydslPredicateExecutor<Event> {
}