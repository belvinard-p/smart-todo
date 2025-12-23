package com.belvi.todolist.service;

import com.belvi.todolist.dto.TodoRequest;
import com.belvi.todolist.dto.TodoResponse;
import com.belvi.todolist.exception.ResourceNotFoundException;
import com.belvi.todolist.mapper.TodoMapper;
import com.belvi.todolist.model.Todo;
import com.belvi.todolist.repository.TodoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TodoServiceImpl implements TodoService {

    public final TodoRepository repository;
    public final TodoMapper mapper;

    @Override
    public TodoResponse create(TodoRequest request) {
        if (repository.existsByTitle(request.getTitle())) {
            throw new IllegalArgumentException(
                    "Todo with title '" + request.getTitle() + "' already exists"
            );
        }

        Todo todo = mapper.toEntity(request);
        todo.setCompleted(false); // règle métier
        Todo saved = repository.save(todo);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TodoResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toResponse);
    }

    @Override
    public TodoResponse update(Long id, TodoRequest request) {
        Todo todo = findTodoOrThrow(id);

        if (todo.isCompleted()) {
            throw new IllegalArgumentException("Cannot update a completed todo");
        }

        if (!todo.getTitle().equals(request.getTitle())
                && repository.existsByTitle(request.getTitle())) {
            throw new IllegalArgumentException(
                    "Todo with title '" + request.getTitle() + "' already exists"
            );
        }

        mapper.updateEntityFromRequest(request, todo);

        Todo updated = repository.save(todo);
        return mapper.toResponse(updated);
    }

    @Override
    public TodoResponse toggle(Long id) {
        Todo todo = findTodoOrThrow(id);
        todo.setCompleted(!todo.isCompleted());
        Todo updated = repository.save(todo);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private Todo findTodoOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Todo not found with id " + id)
                );
    }
}
