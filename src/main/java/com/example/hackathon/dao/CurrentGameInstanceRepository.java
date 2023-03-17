package com.example.hackathon.dao;

import com.example.hackathon.model.CurrentGameInstance;
import com.example.hackathon.model.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentGameInstanceRepository extends CrudRepository<CurrentGameInstance,Long> {

    CurrentGameInstance save(CurrentGameInstance currentGameInstance);
}
