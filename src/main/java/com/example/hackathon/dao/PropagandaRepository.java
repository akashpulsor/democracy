package com.example.hackathon.dao;

import com.example.hackathon.model.PropagandaFreeMedia;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropagandaRepository extends CrudRepository<PropagandaFreeMedia,Long>,
        QuerydslPredicateExecutor<PropagandaFreeMedia> {
}
