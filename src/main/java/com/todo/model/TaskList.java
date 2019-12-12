package com.todo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "task_list")
public class TaskList {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private int id;
	
	@NotNull
	@Column(name = "list_title", nullable = false)
	@Size(min = 2, message = "List title should be at least 2 characters", max=100)
    private String listTitle;
	
	@OneToMany(mappedBy = "taskList", 
			fetch = FetchType.LAZY,
			cascade = {CascadeType.ALL})
	private List <Task> tasks;
 
    public TaskList() {
    }
 
    public TaskList(String listTitle) {
         this.listTitle = listTitle;
    }
 
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
 
    
    public String getListTitle() {
        return listTitle;
    }
    public void setListTitle(String listTitle) {
        this.listTitle = listTitle;
    }
    
    public List <Task> getTasks() {
        return tasks;
    }

    public void setTasks(List <Task> tasks) {
        this.tasks = tasks;
    }
}
