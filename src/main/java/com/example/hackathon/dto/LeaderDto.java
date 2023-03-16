package com.example.hackathon.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class LeaderDto {
    private int leaderId;
    private List<Events> eventsList = new ArrayList<>();
}
