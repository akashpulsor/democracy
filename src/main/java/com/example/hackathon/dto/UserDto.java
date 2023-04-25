package com.example.hackathon.dto;

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class UserDto {
    //Todo add validations in java
    private Long id;

    private String userName;


    private String number;

    private String email;

    private String password;

    private String profileImage;

    private long followerCount;

}
