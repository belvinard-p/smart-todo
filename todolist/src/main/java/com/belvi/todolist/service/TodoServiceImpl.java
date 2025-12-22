package com.belvi.todolist.service;

import com.belvi.todolist.dto.TodoRequest;
import com.belvi.todolist.dto.TodoResponse;
import com.belvi.todolist.mapper.TodoMapper;
import com.belvi.todolist.model.Todo;
import com.belvi.todolist.repository.TodoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TodoServiceImpl implements TodoService {

    private final TodoRepository repository;
    private final TodoMapper mapper;

    @Override
    public TodoResponse create(TodoRequest request) {
        System.out.println("Request title: " + request.getTitle());
        if (repository.existsByTitle(request.getTitle())) {
            throw new IllegalArgumentException("Todo with title '" + request.getTitle() + "' already exists");
        }
        Todo todo = mapper.toEntity(request);
        System.out.println("Todo title after mapping: " + todo.getTitle());
        todo.setCompleted(false); // règle métier
        Todo saved = repository.save(todo);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TodoResponse> findAll() {
        return mapper.toResponseList(repository.findAll());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public TodoResponse update(Long id, TodoRequest request) {
        Todo todo = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Todo not found with id " + id
                ));

        if (!todo.getTitle().equals(request.getTitle()) && repository.existsByTitle(request.getTitle())) {
            throw new IllegalArgumentException("Todo with title '" + request.getTitle() + "' already exists");
        }

        mapper.updateEntityFromRequest(request, todo);

        Todo updated = repository.save(todo);
        return mapper.toResponse(updated);
    }

}
