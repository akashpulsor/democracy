package com.example.hackathon.dto;

import lombok.Data;

@Data
public class GameResponseDto {

    private long gameId;
    private long leaderId;
    private int eventCount;
    private  EventDto eventDto;
    private long currentGameInstanceId;
}
