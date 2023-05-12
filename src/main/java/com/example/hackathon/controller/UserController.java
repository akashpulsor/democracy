package com.example.hackathon.controller;


import com.example.hackathon.dto.*;
import com.example.hackathon.service.UserManager;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
@Api(value = "User Management System")
public class UserController {

    private final UserManager userManager;

    @Autowired
    ObjectMapper objectMapper;

    public UserController(UserManager userManager) {
        this.userManager = userManager;
    }

    @ApiOperation(value = "Create users")
    @PostMapping(value = "/create")
    public UserDto createUser(@RequestBody UserDto userDto){
        return this.userManager.createUser(userDto);
    }


    @ApiOperation(value = "Get user by email")
    @GetMapping(value = "/get_user_by_email/{email}")
    public List<SearchResponseDto> getUsersByEmail(@PathVariable String email) {
        return this.userManager.getUsersByEmail(email);
    }

    @ApiOperation(value = "Login information")
    @PostMapping(value="/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto){
        return this.userManager.login(loginRequestDto);
    }

    @ApiOperation(value = "Get user by id")
    @GetMapping(value = "/get_user_by_id/{id}")
    public UserDto getUsersById(@PathVariable long id) {
        return this.userManager.getUserById(id);
    }

    @ApiOperation(value = "Search user by name")
    @GetMapping(value = "/username")
    public List<SearchResponseDto> getUsersByUserName(@RequestParam(name="name")  String userName) {
        return this.userManager.getUsersByUserName(userName);
    }

    @ApiOperation(value = "get post by id")
    @GetMapping(value = "/post/{id}")
    public PostResponseDto getUsersByUserName(@PathVariable long id) {
        return this.userManager.getPostsByUserId(id);
    }

    @ApiOperation(value = "get post by id")
    @PostMapping(value = "/add_post")
    public PostResponseDto.MediaResponseDto addPost(@RequestBody UploadVideoRequestDto uploadVideoRequestDto) {
        return this.userManager.addExternalPost(uploadVideoRequestDto);
    }

    @ApiOperation(value = "like post")
    @PostMapping(value="/like")
    public LikeResponseDto addLike(@RequestBody LikeRequestDto likeRequestDto){
        return this.userManager.addLike(likeRequestDto);
    }

    @ApiOperation(value = "like post")
    @PostMapping(value="/unlike")
    public LikeResponseDto removeLike(@RequestBody LikeRequestDto likeRequestDto){
        return this.userManager.removeLike(likeRequestDto);
    }


    @ApiOperation(value = "like post")
    @PostMapping(value="/profile/picture/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UserDto updateProfile(@PathVariable long id,

                                         @RequestParam("image") MultipartFile file) throws JsonProcessingException {

        return this.userManager.updateProfile(id, file);
    }

    /*
    * TODO follower service
    *  add follower remove follower
    *  get Profile by id -- user data and video list
    *  upload video/ delete video
    *  like video/unlike video
    *  share video
    *  send message recieve message
    *  add Comment delete comment
    *  feed service
    *  
    *
    * Feed service
    * */

}
