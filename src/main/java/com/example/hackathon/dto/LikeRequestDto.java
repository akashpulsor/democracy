package com.example.hackathon.dto;

import lombok.Data;

@Data
public class LikeRequestDto {
    private long postId;
    private long userId;
}
