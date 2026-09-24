package com.haianh.personalschedule.repository;

import com.haianh.personalschedule.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}