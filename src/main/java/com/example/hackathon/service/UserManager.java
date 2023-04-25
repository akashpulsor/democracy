package com.example.hackathon.service;

import com.example.hackathon.dto.LoginRequestDto;
import com.example.hackathon.dto.LoginResponseDto;
import com.example.hackathon.dto.SearchResponseDto;
import com.example.hackathon.dto.UserDto;

import java.util.List;

public interface UserManager {

    List<SearchResponseDto> getUsersByEmail(String email);

    UserDto createUser(UserDto userDto);

    LoginResponseDto login(LoginRequestDto loginRequestDto);

    UserDto getUserById(long id);
}
