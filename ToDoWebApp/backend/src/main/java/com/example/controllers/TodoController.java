package com.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Todo;
import com.example.service.TodoService;

@RestController
public class TodoController {
	
	@Autowired
	TodoService service;
	
	// To Do
	@PostMapping("create")
	public String createTodo() {
		return "created";
	}
	
	// Done
	@GetMapping("todos")
	public List<Todo> getAllTodos() {
		return service.getAllTodos();
	}
	
	// To Do
	@PutMapping("update")
	public String updateTodo() {
		return "updated";
	}
	
	// To Do
	@DeleteMapping("delete")
	public String deleteTodo() {
		return "deleted";
	}
	
	
}
