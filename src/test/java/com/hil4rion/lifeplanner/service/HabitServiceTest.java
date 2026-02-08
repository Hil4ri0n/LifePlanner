package com.hil4rion.lifeplanner.service;

import com.hil4rion.lifeplanner.model.Habit;
import com.hil4rion.lifeplanner.model.UserProfile;
import com.hil4rion.lifeplanner.repository.HabitRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HabitServiceTest {

    @Mock
    private HabitRepository habitRepository;

    @InjectMocks
    private HabitService habitService;

    private Habit testHabit;

    @BeforeEach
    void setUp() {
        UserProfile testUserProfile = new UserProfile();
        testHabit = Habit.builder()
                .name("habit name")
                .userProfile(testUserProfile)
                .build();
    }

    @Test
    void should_CreateHabit() {
        // when
        habitService.createHabit(testHabit);

        // then
        verify(habitRepository).save(testHabit);
    }

    @Test
    void should_ReturnHabit_when_HabitExists() {
        // given
        when(habitRepository.findById(testHabit.getName())).thenReturn(Optional.of(testHabit));

        // when
        Habit returnedHabit = habitService.getHabit(testHabit.getName());

        // then
        assertEquals(testHabit, returnedHabit);
        verify(habitRepository).findById(testHabit.getName());
    }

    @Test
    void should_Throw_when_HabitDoesntExist() {
        // given
        when(habitRepository.findById(testHabit.getName())).thenReturn(Optional.empty());

        // when then
        assertThrows(EntityNotFoundException.class, () -> {
            habitService.getHabit(testHabit.getName());
        });
    }

    @Test
    void should_UpdateHabitName_when_HabitExists() {
        // given
        when(habitRepository.findById(any(String.class))).thenReturn(Optional.of(testHabit));
        String newName = "new name";

        // when
        Habit updatedHabit = habitService.updateName(testHabit.getName(), newName);

        // then
        assertEquals(newName, updatedHabit.getName());
    }

    @Test
    void should_Throw_when_HabitToUpdateDoesntExist() {
        // given
        when(habitRepository.findById(testHabit.getName())).thenReturn(Optional.empty());

        // when then
        assertThrows(EntityNotFoundException.class, () -> {
            habitService.updateName(testHabit.getName(), "new name");
        });
    }

    @Test
    void should_DeleteHabit() {
        // when
        habitService.deleteHabit(testHabit.getName());

        // then
        verify(habitRepository).deleteById(testHabit.getName());
    }
}
