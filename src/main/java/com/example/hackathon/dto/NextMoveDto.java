package com.example.hackathon.dto;

import lombok.Data;

@Data
public class NextMoveDto {
    private int currentCount;
    private int currentGameInstanceId;
    private boolean support;
    private long playerId;
    private long eventId;
    private long leaderId;
    private boolean freeMediaEnabled;
}
