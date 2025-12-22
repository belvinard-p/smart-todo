package com.belvi.todolist.repository;

import com.belvi.todolist.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    boolean existsByTitle(String title);
}
