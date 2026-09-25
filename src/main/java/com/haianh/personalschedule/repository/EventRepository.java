package com.haianh.personalschedule.repository;

import com.haianh.personalschedule.entity.Event;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
    boolean existsByStartTimeLessThanAndEndTimeGreaterThan(
            LocalDateTime endTime, LocalDateTime startTime);
}