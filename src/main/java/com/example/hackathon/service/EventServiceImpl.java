package com.example.hackathon.service;

import com.example.hackathon.dao.EventRepository;
import com.example.hackathon.model.Event;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements  EventsService{

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }
    @Override
    public List<Event> addEvents(List<Event> events) {
        return null;
    }

    @Override
    public List<Event> getEvents() {
        return null;
    }

    public Event getEventById(long eventId){
        return this.eventRepository.getEventById(eventId);
    }
}
