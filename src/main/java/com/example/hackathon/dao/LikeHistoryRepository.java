package com.example.hackathon.dao;

import com.example.hackathon.model.LikeHistory;
import com.example.hackathon.model.User;
import org.springframework.data.repository.CrudRepository;

public interface LikeHistoryRepository extends CrudRepository<LikeHistory,Long> {


}
