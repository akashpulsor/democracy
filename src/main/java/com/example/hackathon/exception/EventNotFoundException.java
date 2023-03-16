package com.example.hackathon.exception;

import java.util.function.Supplier;

public class EventNotFoundException  extends  RuntimeException {

    public EventNotFoundException(String message){
        super(message);
    }
}
