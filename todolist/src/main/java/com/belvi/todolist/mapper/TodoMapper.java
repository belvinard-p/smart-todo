package com.belvi.todolist.mapper;

import com.belvi.todolist.dto.TodoRequest;
import com.belvi.todolist.dto.TodoResponse;
import com.belvi.todolist.model.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoMapper {

    Todo toEntity(TodoRequest request);

    TodoResponse toResponse(Todo todo);

    List<TodoResponse> toResponseList(List<Todo> todos);

    void updateEntityFromRequest(
            TodoRequest request,
            @MappingTarget Todo todo
    );
}
