package com.hil4rion.lifeplanner.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Set<Task> tasks = new HashSet<>();
    private Set<Note> notes = new HashSet<>();
    private Set<ExpenseCategory> expenseCategories = new HashSet<>();
    private Set<Expense> expenses = new HashSet<>();
    private Set<Habit> habits = new HashSet<>();
    private Set<HabitEntry> habitEntries = new HashSet<>();
}
