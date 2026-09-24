package com.haianh.personalschedule.repository;

import com.haianh.personalschedule.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}