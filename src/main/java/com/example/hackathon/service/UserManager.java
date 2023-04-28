package com.example.hackathon.service;

import com.example.hackathon.dto.*;
import org.springframework.http.ResponseCookie;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserManager {

    List<SearchResponseDto> getUsersByEmail(String email);

    UserDto createUser(UserDto userDto);

    LoginResponseDto login(LoginRequestDto loginRequestDto);

    UserDto signUp(SignUpRequestDto signUpRequestDto);

    UserDto getUserById(long id);

    LoginResponseDto logout();

    TokenRefreshResponse refreshToken(TokenRefreshRequest request);
}
