package com.example.hackathon.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Events {
    private int eventId;
    private List<States> propagandaList = new ArrayList<>();
    private String eventUrl;
    private Map<States, List<States>> propagandaFreeMediaMap = new HashMap<>();
    private String reportUrl;
}
