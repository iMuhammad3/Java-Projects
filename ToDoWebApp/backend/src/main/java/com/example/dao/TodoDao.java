package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Todo;

@Repository
public interface TodoDao extends JpaRepository<Todo, Integer> {
	
	

}
