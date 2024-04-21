package com.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Todo;
import com.example.service.TodoService;

@RestController
@RequestMapping("app")
public class TodoController {
	
	@Autowired
	TodoService service;
	
	// To Do
	@PostMapping("create")
	public ResponseEntity<String> createTodo(
			@RequestParam("todo") String todo,
		    @RequestParam("completed") boolean completed
		    ) {
		return service.addTodo(todo, completed);
	}
	
	// Done
	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@GetMapping("todos")
	public ResponseEntity<List<Todo>> getAllTodos() {
		return service.getAllTodos();
	}
	
	// To Do
	@PutMapping("update")
	public String updateTodo() {
		return "updated";
	}
	
	// To Do
	@DeleteMapping("delete")
	public ResponseEntity<String> deleteTodo(@RequestParam int id) {
		return service.deleteTodo(id);
	}
	
	
}
