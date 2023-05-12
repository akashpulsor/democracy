package com.example.hackathon.service;

import com.example.hackathon.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserManager {

    List<SearchResponseDto> getUsersByEmail(String email);

    List<SearchResponseDto> getUsersByUserName(String userName);

    PostResponseDto getPostsByUserId(long id);

    PostResponseDto.MediaResponseDto addExternalPost(UploadVideoRequestDto uploadVideoRequestDto);

    UserDto createUser(UserDto userDto);

    LoginResponseDto login(LoginRequestDto loginRequestDto);

    UserDto signUp(SignUpRequestDto signUpRequestDto);

    UserDto getUserById(long id);

    LoginResponseDto logout();

    TokenRefreshResponse refreshToken(TokenRefreshRequest request);

    LikeResponseDto addLike(LikeRequestDto likeRequestDto);

    LikeResponseDto removeLike(LikeRequestDto likeRequestDto);

    UserDto updateProfile(long id, MultipartFile file);
}
