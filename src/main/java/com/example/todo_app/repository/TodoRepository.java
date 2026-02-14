package com.example.todo_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todo_app.model.Todo;

@Repository
public interface TodoRepository extends  JpaRepository<Todo, Long> {
    // This is a placeholder for the actual repository implementation
}