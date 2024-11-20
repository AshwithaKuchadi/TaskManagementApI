package com.taskmanager.controller;

import com.taskmanager.exception.ResourceNotFoundException;
import com.taskmanager.model.Task;
import com.taskmanager.service.TaskService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Optional;

/**
 * REST controller for managing tasks.
 * Handles CRUD operations and additional actions like marking tasks as complete.
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Get all tasks
    @GetMapping
    public Iterable<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    // Get a specific task by ID
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with ID " + id + " not found"));
    }

    // Create a new task
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@Valid @RequestBody Task task) {
        return taskService.createTask(task);
    }

    // Update an existing task
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @Valid @RequestBody Task task) {
        return taskService.updateTask(id, task)
                .orElseThrow(() -> new ResourceNotFoundException("Task with ID " + id + " not found"));
    }

    // Delete a task
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id) {
        boolean isDeleted = taskService.deleteTask(id);
        if (!isDeleted) {
            throw new ResourceNotFoundException("Task with ID " + id + " not found");
        }
    }

    // Mark a task as complete
    @PatchMapping("/{id}/complete")
    public Task completeTask(@PathVariable Long id) {
        return taskService.completeTask(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with ID " + id + " not found"));
    }
}
