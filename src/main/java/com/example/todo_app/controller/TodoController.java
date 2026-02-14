package com.example.todo_app.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.todo_app.model.Todo;
import com.example.todo_app.repository.TodoRepository;


@RestController
@RequestMapping("/api/todos")
public class TodoController {
    @Autowired
    private final TodoRepository todoRepository;
    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }
    @GetMapping
    public List<Todo> getAllTodos(){
        return todoRepository.findAll();
    }
    @PostMapping
    public Todo createTodo(@RequestBody Todo todo){
        return todoRepository.save(todo);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Todo>getTodoByid(@PathVariable Long id){
        return todoRepository.findById(id).map(todo ->ResponseEntity.ok().body(todo)).orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id,@RequestBody Todo todoDetails){
        return todoRepository.findById(id)
        .map(todo ->{
            todo.setTitle(todoDetails.getTitle());
            todo.setCompleted(todoDetails.isCompleted());
            Todo updatedTodo = todoRepository.save(todo);
            return ResponseEntity.ok().body(updatedTodo);
        }).orElse(ResponseEntity.notFound().build());}
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTodo(@PathVariable Long id){
        return todoRepository.findById(id)
        .map(todo ->{
            todoRepository.delete(todo);
            return ResponseEntity.ok().build();
      })
      .orElse(ResponseEntity.notFound().build());
    
}}

