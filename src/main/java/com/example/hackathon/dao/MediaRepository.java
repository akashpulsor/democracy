package com.example.hackathon.dao;

import com.example.hackathon.model.Media;
import com.example.hackathon.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MediaRepository  extends CrudRepository<Media,Long> {

    List<Media> findByUserId(long id);

    Optional<Media> findByUserIdAndId(long userId, long postId);
}
