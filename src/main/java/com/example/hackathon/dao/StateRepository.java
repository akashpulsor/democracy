package com.example.hackathon.dao;

import com.example.hackathon.model.State;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends CrudRepository<State,Long>, QuerydslPredicateExecutor<State> {
}
