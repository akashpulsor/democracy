package com.example.hackathon.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PlayerDto {
    private long playerId;
    private String playerName;
    private boolean freeMediaEnabled;
}
