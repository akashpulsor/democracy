package com.example.hackathon.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class EventDto {
    private long eventId;
    private List<StatesDto> propagandaList = new ArrayList<>(); /// states [r
    private StatesDto eventState;
    private Map<StatesDto, List<StatesDto>> propagandaFreeMediaMap = new HashMap<>();
    private StatesDto reportStatesDto;
}
