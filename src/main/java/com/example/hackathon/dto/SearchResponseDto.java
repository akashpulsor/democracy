package com.example.hackathon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SearchResponseDto {

    @JsonProperty("username")
    private String userName;
    private long id;
    private String email;
    private String profileImage;
}
