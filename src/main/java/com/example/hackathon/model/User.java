package com.example.hackathon.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="User")
public class User {
    //Todo add database level  validations in java
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="user_name")
    private String userName;

    @Column(unique=true, name="phone_number")
    private String number;

    @Column(unique=true, name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="account_non_expired")
    private boolean accountNonExpired;

    @Column(name="account_non_locked")
    private boolean accountNonLocked;

    @Column(name="credentials_non_expired")
    private boolean credentialsNonExpired;

    @Column(name="profile_image")
    private String profileImage;

    private long followerCount;


}
