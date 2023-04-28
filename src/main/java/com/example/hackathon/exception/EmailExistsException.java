package com.example.hackathon.exception;

public class EmailExistsException extends  RuntimeException{

    public EmailExistsException(String  message) {
        super(message);
    }
}
