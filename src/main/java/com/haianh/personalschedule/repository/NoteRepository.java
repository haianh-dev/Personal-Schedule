package com.haianh.personalschedule.repository;

import com.haianh.personalschedule.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}