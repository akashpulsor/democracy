package com.example.hackathon.dto;

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class LoginResponseDto {

    private String accessToken;

    private String refreshToken;

    private String type = "Bearer";

    private UserInfoResponse userInfoResponse;
}
