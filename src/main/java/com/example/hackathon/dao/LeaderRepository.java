package com.example.hackathon.dao;

import com.example.hackathon.model.Leader;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaderRepository extends CrudRepository<Leader,Long>, QuerydslPredicateExecutor<Leader> {
}
