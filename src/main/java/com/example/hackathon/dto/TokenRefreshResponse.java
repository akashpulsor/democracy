package com.example.hackathon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class TokenRefreshResponse {

    private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer";
}
