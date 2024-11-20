package com.taskmanager.repository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.taskmanager.model.Task;

@Repository
public class TaskRepository {

    private final Map<Long, Task> taskDatabase = new ConcurrentHashMap<>();
    private static long taskIdCounter = 1;

    // Create a new task
    public Task save(Task task) {
        if (task.getId() == null) {
            task.setId(taskIdCounter++);
            task.setCreatedAt(LocalDateTime.now());
        }
        else {
        task.setCreatedAt(taskDatabase.get(task.getId()).getCreatedAt());
        task.setUpdatedAt(LocalDateTime.now());
        }
        taskDatabase.put(task.getId(), task);
        return task;
    }

    // Get all tasks
    public Iterable<Task> findAll() {
        return taskDatabase.values();
    }

    // Find task by ID
    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(taskDatabase.get(id));
    }

    // Check if a task exists
    public boolean existsById(Long id) {
        return taskDatabase.containsKey(id);
    }

    // Delete a task by ID
    public void deleteById(Long id) {
        taskDatabase.remove(id);
    }
}
