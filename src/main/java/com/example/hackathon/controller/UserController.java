package com.example.hackathon.controller;


import com.example.hackathon.dto.LoginRequestDto;
import com.example.hackathon.dto.LoginResponseDto;
import com.example.hackathon.dto.SearchResponseDto;
import com.example.hackathon.dto.UserDto;
import com.example.hackathon.model.Game;
import com.example.hackathon.service.UserManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Api(value = "User Management System")
public class UserController {

    private final UserManager userManager;

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



}
