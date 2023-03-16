package com.example.hackathon.dto;

import lombok.Data;

@Data
public class JoinRequestDto {

    private int gameId;

    private PlayerDto playerDto;
}
