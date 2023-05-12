package com.example.hackathon.service;

import com.example.hackathon.dto.LikeResponseDto;
import com.example.hackathon.dto.PostResponseDto;
import com.example.hackathon.dto.UploadVideoRequestDto;
import com.example.hackathon.exception.StorageException;
import com.example.hackathon.model.Media;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;

@Slf4j
@Component
public class MediaManagerImpl implements MediaManager {

    private final MediaService mediaService;

    @Value("${Image.directory.url}")
    private String rootLocation;

    public MediaManagerImpl(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @Override
    public PostResponseDto getPostsByUserId(long id) {
        List<Media> mediaList = this.mediaService.getPostsByUserId(id);
        return mediaListToProfileResponseDto(mediaList);
    }

    @Override
    public LikeResponseDto addLike(long postId, long userId) {
        long updatedLikeCount = this.mediaService.likePost(postId, userId);
        LikeResponseDto likeResponseDto = new LikeResponseDto();
        likeResponseDto.setLikeCount(updatedLikeCount);
        likeResponseDto.setPostId(postId);
        likeResponseDto.setUserId(userId);
        return likeResponseDto;
    }

    @Override
    public LikeResponseDto removeLike(long postId, long userId) {
        long updatedLikeCount = this.mediaService.disLikePost(postId,userId);
        LikeResponseDto likeResponseDto = new LikeResponseDto();
        likeResponseDto.setLikeCount(updatedLikeCount);
        likeResponseDto.setPostId(postId);
        likeResponseDto.setUserId(userId);
        return likeResponseDto;
    }

    @Override
    public PostResponseDto.MediaResponseDto addPost(UploadVideoRequestDto uploadVideoRequestDto) {
        Media media = UploadVideoRequestDtoToMedia(uploadVideoRequestDto);
        this.mediaService.addMedia(media);
        return mediaToPostResponsesDto(media);
    }

    @Override
    public Path createMediaPath(long id) throws IOException {
        Path userMediaDirectory = Paths.get(rootLocation + "/" + id);
        createDirectory(userMediaDirectory);
        return userMediaDirectory;
    }

    @Override
    public Path storeProfilePic(long id, MultipartFile file) throws IOException {
        createMediaPath(id);
        Path profilePicDirectory = Paths.get(rootLocation + "/" + id+"/"+"profilePic/");
        createDirectory(profilePicDirectory);
        store(file,profilePicDirectory,"profile_pic_"+id+".jpg");
        return profilePicDirectory.resolve("profile_pic_"+id+".jpg");
    }

    private PostResponseDto mediaListToProfileResponseDto(List<Media> mediaList) {
        PostResponseDto postResponseDto = new PostResponseDto();
        List<PostResponseDto.MediaResponseDto> mediaResponseDtoList = new ArrayList<>();
        for (Media media:
        mediaList) {
            PostResponseDto.MediaResponseDto mediaResponseDto = mediaToPostResponsesDto(media);
            mediaResponseDtoList.add(mediaResponseDto);
        }
        postResponseDto.setMediaData(mediaResponseDtoList);
        return postResponseDto;
    }
    
    private PostResponseDto.MediaResponseDto mediaToPostResponsesDto(Media media) {
        PostResponseDto.MediaResponseDto mediaResponseDto = new PostResponseDto.MediaResponseDto();
        mediaResponseDto.setMediaSource(media.getMediaSource());
        mediaResponseDto.setPostId(media.getId());
        mediaResponseDto.setSourceUrl(media.getSourceUrl());
        mediaResponseDto.setUserId(media.getUserId());
        mediaResponseDto.setThumbnailUrl(media.getThumbnailUrl());
        return mediaResponseDto;
    }

    private Media UploadVideoRequestDtoToMedia(UploadVideoRequestDto uploadVideoRequestDto) {
        Media media = new Media();
        media.setMediaSource(uploadVideoRequestDto.getMediaSource());
        media.setSourceUrl(uploadVideoRequestDto.getPostLink());
        media.setUserId(uploadVideoRequestDto.getUserId());
        media.setThumbnailUrl(uploadVideoRequestDto.getThumbnailLink());
        return media;
    }


    private void store(MultipartFile file, Path directory, String fileName ) {
        // TODO Auto-generated method stub
        String orignalFilename = StringUtils.cleanPath( file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + fileName );
            }
            if (orignalFilename.contains("..")) {
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + orignalFilename);
            }


            Files.copy(file.getInputStream(), directory.resolve(fileName),
                    StandardCopyOption.REPLACE_EXISTING );
        }
        catch (IOException e) {
            log.error("Failed to store image " + orignalFilename, e);
            throw new StorageException("Failed to store file " + orignalFilename, e);
        }


    }

    public Long getEpochMillis() {
        return new Date().getTime();
    }

    public  Boolean createDirectory (  Path directoryPath  ) throws  IOException {
        if ( ! existsDirectory( directoryPath ) ) {
                Files.createDirectories(directoryPath);
                return true;
        }
        return false;
    }

    public  Boolean existsDirectory( Path directoryPath ) {
        return Files.exists(directoryPath);
    }
}
