package com.example.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Todo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String todo;
	private boolean completed;
	@CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_created", nullable = false, updatable = false)
    private Date dateCreated;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_modified")
    private Date lastModified;
	
	public Todo () {
		
	}
	
	public Todo(String todo, boolean completed) {
		this.todo = todo;
		this.completed = completed;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTodo() {
		return todo;
	}
	public void setTodo(String todo) {
		this.todo = todo;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	public Date getDate_created() {
		return dateCreated;
	}
	public void setDate_created(Date date_created) {
		this.dateCreated = date_created;
	}
	public Date getLast_modified() {
		return lastModified;
	}
	public void setLast_modified(Date last_modified) {
		this.lastModified = last_modified;
	}
	
	

}
