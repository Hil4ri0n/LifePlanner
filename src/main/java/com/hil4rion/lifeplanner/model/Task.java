package com.hil4rion.lifeplanner.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
@Setter
@Getter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private LocalDate deadline;
    private boolean completed;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserProfile userProfile;

    public Task(String name, LocalDate deadline) {
        this.name = name;
        this.deadline = deadline;
        completed = false;
    }

    public Task() {
        completed = false;
    }
}
