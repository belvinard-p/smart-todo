package com.belvi.todolist.dto;

import com.belvi.todolist.model.Priority;
import com.belvi.todolist.model.Todo;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TodoRequest {

    @NotBlank
    @Size(min = 3, max = 100)
    private String title;

    private String description;

    private Priority priority;

    @FutureOrPresent(message = "La date d'échéance ne peut pas être dans le passé")
    private LocalDate dueDate;
}
