package com.hil4rion.lifeplanner.repository;

import com.hil4rion.lifeplanner.model.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitRepository extends JpaRepository<Habit, String> {
}
