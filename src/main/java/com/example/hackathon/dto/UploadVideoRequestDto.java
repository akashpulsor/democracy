package com.example.hackathon.dto;

import com.example.hackathon.model.MediaSource;
import lombok.Data;

@Data
public class UploadVideoRequestDto {

    private long userId;

    private String postLink;

    private String thumbnailLink;

    private MediaSource mediaSource;
}
