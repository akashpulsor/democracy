package com.example.hackathon.dto;

import lombok.Data;

@Data
public class NextMoveDto {
    private int currentCount;
    private boolean support;
    private long playerId;
    private boolean freeMediaEnabled;
}
