package com.taskmanager.model;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task {

    private Long id;

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must not exceed 100 characters")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    @NotNull(message = "Due date is required")
    @Future(message = "Due date must be a future date")
    private LocalDate dueDate;

    private Status status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public enum Status {
        PENDING,
        IN_PROGRESS,
        COMPLETED
    }

    
   
    public Task(Long id, String title, String description, LocalDate dueDate, Status status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status != null ? status : Status.PENDING; // Default to PENDING if null
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    
    public Task() {
        this.status = Status.PENDING;
    }

  

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
