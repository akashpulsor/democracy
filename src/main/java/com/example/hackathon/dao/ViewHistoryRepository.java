package com.example.hackathon.dao;

import com.example.hackathon.model.LikeHistory;
import com.example.hackathon.model.ViewHistory;
import org.springframework.data.repository.CrudRepository;

public interface ViewHistoryRepository  extends CrudRepository<ViewHistory,Long> {
}
