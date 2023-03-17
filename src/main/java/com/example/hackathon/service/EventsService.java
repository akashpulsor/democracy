package com.example.hackathon.service;

import com.example.hackathon.model.Event;

import java.util.List;

public interface EventsService {


    public List<Event> addEvents(List<Event> events);

    public List<Event> getEvents();
}
