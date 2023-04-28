package com.example.hackathon.dto;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
public class SignUpRequestDto {

    //@NotBlank
    @Size(min = 3, max = 20)
    private String username;

    //@NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Set<String> role;

    //@NotBlank
    @Size(max = 13)
    private String phone;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    Set<String> roles = new HashSet<>();
}
