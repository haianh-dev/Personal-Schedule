package com.haianh.personalschedule.service;

import com.haianh.personalschedule.entity.Event;
import com.haianh.personalschedule.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event updateEvent(Long id, Event event) {
        Event existingEvent = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        existingEvent.setTitle(event.getTitle());
        existingEvent.setLocation(event.getLocation());
        existingEvent.setStartTime(event.getStartTime());
        existingEvent.setEndTime(event.getEndTime());
        existingEvent.setCategory(event.getCategory());

        return eventRepository.save(existingEvent);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}