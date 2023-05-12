package com.example.hackathon.service;

import com.example.hackathon.dao.LikeHistoryRepository;
import com.example.hackathon.dao.MediaRepository;
import com.example.hackathon.dao.ViewHistoryRepository;
import com.example.hackathon.exception.PostNotFoundException;
import com.example.hackathon.model.Media;
import com.example.hackathon.model.ViewHistory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
public class MediaService {

    private final MediaRepository mediaRepository;

    private final LikeHistoryRepository likeHistoryRepository;

    private final ViewHistoryRepository viewHistoryRepository;

    public MediaService(MediaRepository mediaRepository,
                        LikeHistoryRepository likeHistoryRepository,
                        ViewHistoryRepository viewHistoryRepository) {
        this.mediaRepository = mediaRepository;
        this.likeHistoryRepository = likeHistoryRepository;
        this.viewHistoryRepository = viewHistoryRepository;
    }

    public Media addMedia(Media media) {
        return this.mediaRepository.save(media);
    }
    public List<Media> getPostsByUserId(long id) {
        return this.mediaRepository.findByUserId(id);
    }

    public Media getPostByUserIdAndPostId(long postId, long userId) {
        return this.mediaRepository.findByUserIdAndId(userId, postId).orElseThrow(
                () -> new PostNotFoundException("Post "+postId+" not found "+ userId));
    }

    @Transactional
    public long likePost(long postId, long userId) throws PostNotFoundException{
        Media media = getPostByUserIdAndPostId(postId, userId);
        media.setLikeCount(media.getLikeCount() + 1);
        this.mediaRepository.save(media);
        addViewHistory(postId,userId);
        return media.getLikeCount();
    }

    @Transactional
    public long disLikePost(long postId, long userId) throws PostNotFoundException{
        Media media = getPostByUserIdAndPostId(postId, userId);
        media.setLikeCount(media.getLikeCount() - 1);
        this.mediaRepository.save(media);
        addViewHistory(postId,userId);
        return media.getLikeCount();
    }

    private ViewHistory addViewHistory(long postId, long userId) {
        ViewHistory viewHistory = new ViewHistory();
        viewHistory.setPostId(postId);
        viewHistory.setUserId(userId);
        return this.viewHistoryRepository.save(viewHistory);
    }
}
