package com.hil4rion.lifeplanner.service;

import com.hil4rion.lifeplanner.model.HabitEntry;
import com.hil4rion.lifeplanner.repository.HabitEntryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class HabitEntryService {
    private final HabitEntryRepository habitEntryRepository;

    public HabitEntry createHabitEntry(HabitEntry habitEntry) {
        return habitEntryRepository.save(habitEntry);
    }

    @Transactional(readOnly = true)
    public HabitEntry getHabitEntry(UUID id) {
        return habitEntryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public void deleteHabitEntry(UUID id) {
        habitEntryRepository.deleteById(id);
    }
}
