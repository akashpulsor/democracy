package com.example.hackathon.dto;

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class SignUpResponseDto {

    private String jwtCookie;

    private String jwtRefreshCookie;

    private UserDto userDto;
}
