package com.example.hackathon.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class LeaderDto {
    private long leaderId;
    private Set<EventDto> eventDtoList = new HashSet<>();
}
