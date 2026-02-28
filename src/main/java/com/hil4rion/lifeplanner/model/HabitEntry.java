package com.hil4rion.lifeplanner.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class HabitEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserProfile userProfile;
    private LocalDate date;
    @ManyToOne(fetch = FetchType.LAZY)
    private Habit habit;
}
