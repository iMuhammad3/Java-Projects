package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.dao.TodoDao;
import com.example.model.Todo;

@Service
public class TodoService {
	
	@Autowired
	TodoDao dao;

	public List<Todo> getAllTodos() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	public ResponseEntity<String> addTodo(String todo, boolean completed) {
		// TODO Auto-generated method stub
		Todo newTodo = new Todo(todo, completed);
		dao.save(newTodo);
		return new ResponseEntity<>("success", HttpStatus.CREATED);
	}

}
