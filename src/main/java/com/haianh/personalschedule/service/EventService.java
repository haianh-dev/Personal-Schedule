package com.haianh.personalschedule.service;

import com.haianh.personalschedule.dto.EventRequest;
import com.haianh.personalschedule.entity.Category;
import com.haianh.personalschedule.entity.Event;
import com.haianh.personalschedule.repository.CategoryRepository;
import com.haianh.personalschedule.repository.EventRepository;
import org.springframework.stereotype.Service;
import com.haianh.personalschedule.exception.BadRequestException;
import com.haianh.personalschedule.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final CategoryRepository categoryRepository;

    public EventService(
            EventRepository eventRepository,
            CategoryRepository categoryRepository) {

        this.eventRepository = eventRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public Event createEvent(EventRequest request) {

        // 1. Kiểm tra thời gian
        if (!request.getStartTime().isBefore(request.getEndTime())) {
            throw new BadRequestException(
                    "Start time must be before end time"
            );
        }

        // 2. Tìm Category
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        // 3. Tạo Entity
        Event event = new Event();

        event.setTitle(request.getTitle());
        event.setLocation(request.getLocation());
        event.setStartTime(request.getStartTime());
        event.setEndTime(request.getEndTime());
        event.setCategory(category);

        // 4. Lưu database
        return eventRepository.save(event);
    }

    public Event updateEvent(Long id, EventRequest request) {

        Event existingEvent = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

        // Kiểm tra thời gian
        if (!request.getStartTime().isBefore(request.getEndTime())) {
            throw new BadRequestException(
                    "Start time must be before end time"
            );
        }

        // Tìm Category
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        existingEvent.setTitle(request.getTitle());
        existingEvent.setLocation(request.getLocation());
        existingEvent.setStartTime(request.getStartTime());
        existingEvent.setEndTime(request.getEndTime());
        existingEvent.setCategory(category);

        return eventRepository.save(existingEvent);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}