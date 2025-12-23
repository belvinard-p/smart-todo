package com.belvi.todolist.service;

import com.belvi.todolist.dto.TodoRequest;
import com.belvi.todolist.dto.TodoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TodoService {

    TodoResponse create(TodoRequest request);

    Page<TodoResponse> findAll(Pageable pageable);

    TodoResponse update(Long id, TodoRequest request);

    TodoResponse toggle(Long id);

    void delete(Long id);
}
