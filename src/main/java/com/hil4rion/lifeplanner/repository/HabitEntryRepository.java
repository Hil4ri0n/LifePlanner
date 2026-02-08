package com.hil4rion.lifeplanner.repository;

import com.hil4rion.lifeplanner.model.HabitEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HabitEntryRepository extends JpaRepository<HabitEntry, UUID> {
}
