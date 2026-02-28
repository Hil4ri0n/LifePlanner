package com.hil4rion.lifeplanner.service;

import com.hil4rion.lifeplanner.dto.TaskDto;
import com.hil4rion.lifeplanner.mapper.TaskMapper;
import com.hil4rion.lifeplanner.model.Task;
import com.hil4rion.lifeplanner.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    private TaskMapper taskMapper = Mappers.getMapper(TaskMapper.class);

    private TaskService taskService;

    private Task testTask;
    private TaskDto updateDto;

    @BeforeEach
    void setUp() {
        taskService = new TaskService(taskRepository, taskMapper);
        testTask = new Task(
                "test", LocalDate.now());

        updateDto = new TaskDto("updated", LocalDate.now(), true);
    }

    @Test
    void should_CreateNewTask() {
        // given
        when(taskRepository.save(testTask)).thenReturn(testTask);

        // when
        Task savedTask = taskService.createTask(testTask);

        // then
        assertEquals(testTask.getName(), savedTask.getName());
        assertEquals(testTask.getDeadline(), savedTask.getDeadline());
    }

    @Test
    void should_ReturnTask_WhenTaskExists() {
        // given
        when(taskRepository.findById(any(UUID.class)))
                .thenReturn(Optional.ofNullable(testTask));

        // when
        Task savedTask = taskService.getTask(UUID.randomUUID());

        // then
        assertEquals(testTask.getName(), savedTask.getName());
        assertEquals(testTask.getDeadline(), savedTask.getDeadline());       // then
    }

    @Test
    void should_Throw_WhenTaskDoesntExist() {
        // given
        when(taskRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        // when then
        assertThrows(EntityNotFoundException.class, () -> {
            taskService.getTask(UUID.randomUUID());
        });
    }

    @Test
    void should_UpdateTask_WhenTaskExists() {
        // given
        when(taskRepository.findById(any(UUID.class)))
                .thenReturn(Optional.ofNullable(testTask));

        // when
        Task updatedTask = taskService.updateTask(updateDto, UUID.randomUUID());

        // then
        assertEquals(updateDto.name(), updatedTask.getName());
        assertEquals(updateDto.deadline(), updatedTask.getDeadline());
        assertEquals(updateDto.completed(), updatedTask.isCompleted());
    }

    @Test
    void should_Throw_when_TaskToBeUpdatedDoesntExist() {
        // given
        when(taskRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        // when then
        assertThrows(EntityNotFoundException.class, () -> {
            taskService.updateTask(updateDto, UUID.randomUUID());
        });
    }

    @Test
    void should_DeleteTaskWithGivenId() {
        // given
        UUID exampleId = UUID.randomUUID();

        // when
        taskService.deleteTask(exampleId);

        // then
        verify(taskRepository).deleteById(exampleId);
    }
}