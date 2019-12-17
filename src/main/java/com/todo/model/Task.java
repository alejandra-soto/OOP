package com.todo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "task")
public class Task {
	
	
	private Long id;
    private String taskTitle;
    private TaskList taskList;
 
    public Task() {
  
    }
 
    public Task(String taskTitle, String taskDesc) {
         this.taskTitle = taskTitle;
    }
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
	@Size(min = 2, message = "Task title should be at least 2 characters", max=100)
	@Column(name = "task_title", nullable = false)
    public String getTaskTitle() {
        return taskTitle;
    }
    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "list_id", nullable = false)
    public TaskList getTaskList() {
    	return taskList;
    }
    
    public void setTaskList(TaskList taskList) {
    	this.taskList = taskList;
    }
    
    @Override
    public String toString() {
        return "Task [id=" + id + ", task_title=" + taskTitle + "]";
    }
 
}
