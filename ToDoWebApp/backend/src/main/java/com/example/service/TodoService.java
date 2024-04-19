package com.example.service;

import java.util.ArrayList;
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

	public ResponseEntity<List<Todo>> getAllTodos() {
		// TODO Auto-generated method stub
		try {
			return new ResponseEntity<>(dao.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> addTodo(String todo, boolean completed) {
		// TODO Auto-generated method stub
		try {
			Todo newTodo = new Todo(todo, completed);
			dao.save(newTodo);
			return new ResponseEntity<>("success", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("Could not add todo", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<String> deleteTodo(int id) {
		// TODO Auto-generated method stub
		try {
			dao.deleteById(id);
			return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("Could not delete", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
