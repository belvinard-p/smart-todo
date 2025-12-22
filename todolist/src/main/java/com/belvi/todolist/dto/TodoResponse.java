package com.belvi.todolist.dto;

import com.belvi.todolist.model.Todo;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;

@Data
public class TodoResponse {

    private Long id;
    private String title;
    private String description;
    private boolean completed;
    private Todo.Priority priority;
    private LocalDate dueDate;
    private LocalDate createdAt;
}
