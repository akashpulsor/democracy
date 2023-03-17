package com.example.hackathon.exception;

public class CurrentGameInstanceNotFoundException  extends RuntimeException{

    public CurrentGameInstanceNotFoundException(String message){
        super(message);
    }
}
