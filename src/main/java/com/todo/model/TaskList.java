package com.todo.model;

import java.util.List;
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
	
	
	private Long id;
    private String listTitle;
	private List <Task> tasks;
 
    public TaskList() {
    }
 
    public TaskList(String listTitle) {
        this.listTitle = listTitle;
    }
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "list_id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
 
    @NotNull
	@Column(name = "list_title", nullable = false)
	@Size(min = 2, message = "List title should be at least 2 characters", max=100)    
    public String getListTitle() {
        return listTitle;
    }
    public void setListTitle(String listTitle) {
        this.listTitle = listTitle;
    }
    
    @OneToMany(mappedBy = "taskList", 
			fetch = FetchType.LAZY)
    public List <Task> getTasks() {
        return tasks;
    }

    public void setTasks(List <Task> tasks) {
        this.tasks = tasks;
    }
    
	/*
	 * @Override public String toString() { StringBuffer sb = new StringBuffer();
	 * for(Task t: tasks) { sb.append(t.getTaskTitle()); } return sb.toString(); }
	 */
}
