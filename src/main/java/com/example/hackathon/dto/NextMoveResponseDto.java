package com.example.hackathon.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class NextMoveResponseDto {

    private int currentCount;
    private List<StatesDto> states = new ArrayList<>();
    private long playerId;
    private long currentGameInstanceId;
}
