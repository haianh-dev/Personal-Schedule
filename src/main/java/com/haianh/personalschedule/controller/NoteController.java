package com.haianh.personalschedule.controller;

import com.haianh.personalschedule.entity.Note;
import com.haianh.personalschedule.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                noteService.getNoteById(id));
    }

    @GetMapping("/event/{eventId}")
    public List<Note> getNotesByEventId(
            @PathVariable Long eventId) {

        return noteService.getNotesByEventId(eventId);
    }

    @PostMapping
    public ResponseEntity<Note> createNote(
            @RequestParam Long eventId,
            @RequestBody Note note) {

        return ResponseEntity.ok(
                noteService.createNote(eventId, note));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(
            @PathVariable Long id,
            @RequestParam Long eventId,
            @RequestBody Note note) {

        return ResponseEntity.ok(
                noteService.updateNote(id, eventId, note));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(
            @PathVariable Long id) {

        noteService.deleteNote(id);

        return ResponseEntity.noContent().build();
    }
}