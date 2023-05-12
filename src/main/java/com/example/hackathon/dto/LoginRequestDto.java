package com.example.hackathon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginRequestDto {
    @JsonProperty("username")
    private String userName;
    private String password;
}
