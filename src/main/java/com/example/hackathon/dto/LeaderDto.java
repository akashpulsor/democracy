package com.example.hackathon.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LeaderDto {
    private int leaderId;
    private List<EventDto> eventDtoList = new ArrayList<>();
}
