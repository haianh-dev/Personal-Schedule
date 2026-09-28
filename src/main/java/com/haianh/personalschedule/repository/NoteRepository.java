package com.haianh.personalschedule.repository;

import com.haianh.personalschedule.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByEventId(Long eventId);
}