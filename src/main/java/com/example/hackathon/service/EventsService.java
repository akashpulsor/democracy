package com.example.hackathon.service;

import com.example.hackathon.model.Event;

import java.util.List;

public interface EventsService {


    List<Event> addEvents(List<Event> events);

    List<Event> getEvents();
    Event getEventById(long eventId);
}
