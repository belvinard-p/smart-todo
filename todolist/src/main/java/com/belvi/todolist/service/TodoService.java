package com.belvi.todolist.service;

import com.belvi.todolist.dto.TodoRequest;
import com.belvi.todolist.dto.TodoResponse;

import java.util.List;

public interface TodoService {

    TodoResponse create(TodoRequest request);

    List<TodoResponse> findAll();

    void delete(Long id);

    TodoResponse update(Long id, TodoRequest request);
}
