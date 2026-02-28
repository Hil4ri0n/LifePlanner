package com.hil4rion.lifeplanner.service;

import com.hil4rion.lifeplanner.model.Habit;
import com.hil4rion.lifeplanner.repository.HabitRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class HabitService {
    private final HabitRepository habitRepository;

    public Habit createHabit(Habit habit) {
        return habitRepository.save(habit);
    }

    @Transactional(readOnly = true)
    public Habit getHabit(String name) {
        return habitRepository.findById(name).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Habit updateName(String oldName, String newName) {
        Habit habit = getHabit(oldName);
        habit.setName(newName);
        return habit;
    }

    public void deleteHabit(String name) {
        habitRepository.deleteById(name);
    }
}
