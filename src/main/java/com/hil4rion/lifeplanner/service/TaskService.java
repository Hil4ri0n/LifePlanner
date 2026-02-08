package com.hil4rion.lifeplanner.service;

import com.hil4rion.lifeplanner.dto.TaskDto;
import com.hil4rion.lifeplanner.mapper.TaskMapper;
import com.hil4rion.lifeplanner.model.Task;
import com.hil4rion.lifeplanner.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Transactional(readOnly = true)
    public Task getTask(UUID id) {
        return taskRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Task updateTask(TaskDto taskDto, UUID id) {
        Task task = getTask(id);
        taskMapper.patch(taskDto, task);
        return task;
    }

    public void deleteTask(UUID id) {
        taskRepository.deleteById(id);
    }
}

