package com.haianh.personalschedule.service;

import com.haianh.personalschedule.entity.Event;
import com.haianh.personalschedule.entity.Note;
import com.haianh.personalschedule.exception.ResourceNotFoundException;
import com.haianh.personalschedule.repository.EventRepository;
import com.haianh.personalschedule.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final EventRepository eventRepository;

    public NoteService(
            NoteRepository noteRepository,
            EventRepository eventRepository) {

        this.noteRepository = noteRepository;
        this.eventRepository = eventRepository;
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Note getNoteById(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note not found"));
    }

    public Note createNote(Long eventId, Note note) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

        note.setEvent(event);

        return noteRepository.save(note);
    }

    public Note updateNote(Long id, Long eventId, Note note) {

        Note existingNote = noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note not found"));

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

        existingNote.setTitle(note.getTitle());
        existingNote.setContent(note.getContent());
        existingNote.setEvent(event);

        return noteRepository.save(existingNote);
    }

    public void deleteNote(Long id) {

        if (!noteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Note not found");
        }

        noteRepository.deleteById(id);
    }
}