package com.belvi.todolist.controller;

import com.belvi.todolist.dto.TodoRequest;
import com.belvi.todolist.dto.TodoResponse;
import com.belvi.todolist.service.TodoService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService service;

    @PostMapping
    public ResponseEntity<TodoResponse> create(
            @Valid @RequestBody TodoRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(request));
    }

    @GetMapping
    public ResponseEntity<Page<TodoResponse>> findAll(
            @Parameter(description = "Pagination parameters. Valid sort properties: id, title, description, completed, priority, dueDate, createdAt", 
                      example = "{\"page\": 0, \"size\": 10, \"sort\": [\"title\"]}")
            @PageableDefault(size = 10, sort = "id") Pageable pageable
    ) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody TodoRequest request
    ) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @PatchMapping("/{id}/toggle")
    public ResponseEntity<TodoResponse> toggle(@PathVariable Long id) {
        return ResponseEntity.ok(service.toggle(id));
    }

}
