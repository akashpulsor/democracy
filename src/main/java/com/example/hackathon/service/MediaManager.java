package com.example.hackathon.service;

import com.example.hackathon.dto.LikeResponseDto;
import com.example.hackathon.dto.PostResponseDto;
import com.example.hackathon.dto.UploadVideoRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public interface MediaManager {

    PostResponseDto getPostsByUserId(long id);

    LikeResponseDto addLike(long postId, long userId);


    LikeResponseDto removeLike(long postId, long userId);

    PostResponseDto.MediaResponseDto addPost(UploadVideoRequestDto uploadVideoRequestDto);

    Path createMediaPath(long id) throws IOException;

    Path storeProfilePic(long id, MultipartFile file) throws  IOException;


}
