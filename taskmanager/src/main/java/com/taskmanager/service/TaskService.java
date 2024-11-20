package com.taskmanager.service;

import org.springframework.stereotype.Service;

import com.taskmanager.model.Task;
import com.taskmanager.repository.TaskRepository;

import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Iterable<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task createTask(Task task) {
        task.setStatus(Task.Status.PENDING); // Default status
        return taskRepository.save(task);
    }

    public Optional<Task> updateTask(Long id, Task task) {
        if (!taskRepository.existsById(id)) {
            return Optional.empty();
        }
        task.setId(id);
        return Optional.of(taskRepository.save(task));
    }

    public boolean deleteTask(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Task> completeTask(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        task.ifPresent(t -> {
            t.setStatus(Task.Status.COMPLETED);
            taskRepository.save(t);
        });
        return task;
    }
}
