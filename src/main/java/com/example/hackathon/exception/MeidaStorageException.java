package com.example.hackathon.exception;

public class MeidaStorageException extends  RuntimeException{

    public MeidaStorageException(String  message) {
        super(message);
    }

    public MeidaStorageException(String  message, Exception e) {
        super(message,e);
    }
}

