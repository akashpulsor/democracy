package com.example.hackathon.exception;

public class UserNameExistsException extends  RuntimeException{

    public UserNameExistsException(String message) {
        super(message);
    }
}
