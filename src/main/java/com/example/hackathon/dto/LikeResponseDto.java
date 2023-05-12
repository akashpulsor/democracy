package com.example.hackathon.dto;

import lombok.Data;

@Data
public class LikeResponseDto {

    private long likeCount;
    private long postId;
    private long userId;
}
