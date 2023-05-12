package com.example.hackathon.exception;

public class PostNotFoundException extends  RuntimeException{

    public PostNotFoundException(String message) {
        super(message);
    }
}
