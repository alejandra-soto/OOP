package com.todo.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.todo.exception.ResourceNotFoundException;
import com.todo.model.Task;
import com.todo.model.TaskList;
import com.todo.repository.TaskListRepository;
import com.todo.repository.TaskRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController 
@RequestMapping("/")
public class TaskController {
	
	@Autowired
    private TaskRepository taskRepository;
	
	@Autowired
    private TaskListRepository taskListRepository;

	@GetMapping("/tasklist/{taskListId}/tasks")
    public List <Task> getAllTasksByTaskList(@PathVariable(value = "taskListId") Long taskListId) {
        //return taskRepository.findByTaskListId(taskListId);
		List<Task> tasks = taskRepository.findByTaskListId(taskListId);
		ArrayList<Task> arr = new ArrayList<>();
		
		Task rTask = new Task();
		Iterator<Task> itr = tasks.iterator();
		while(itr.hasNext()){
			Task t = itr.next();
			rTask.setId(t.getId());
			rTask.setTaskTitle(t.getTaskTitle());
			
			TaskList tl = t.getTaskList();
			
			TaskList tl1 = new TaskList();
			tl1.setId(tl.getId());
			tl1.setListTitle(tl.getListTitle());
			rTask.setTaskList(null);
			arr.add(rTask);
			}	
		
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
    	System.out.println(rTask.toString());
    	return arr;
    	
    }

    @PostMapping("/tasklist/{taskListId}/tasks")
    public Task createTask(@PathVariable(value = "taskListId") Long taskListId,
        @Valid @RequestBody Task task) throws ResourceNotFoundException {
    	
    	System.out.println("In Creating Tassks");
    	Task t = null;
    	Optional<TaskList> taskList = taskListRepository.findById(taskListId);
    	if(taskList == null) {
    		throw new ResourceNotFoundException("tasklist not found ");
    	} else {
    		task.setTaskList(taskList.get());
    		t= taskRepository.save(task);
    	}
    	
    	

    		Task rTask = new Task();
    		
    		rTask.setId(t.getId());
    		rTask.setTaskTitle(t.getTaskTitle());
    		
    		TaskList tl = t.getTaskList();
    		
    		TaskList tl1 = new TaskList();
    		tl1.setId(tl.getId());
    		tl1.setListTitle(tl.getListTitle());
    		rTask.setTaskList(null);
    		
    		
    		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        	System.out.println(rTask.toString());
    	
    	return rTask;
    	
		/*
		 * return taskListRepository.findById(taskListId).map(taskList -> {
		 * task.setTaskList(taskList); return taskRepository.save(task);
		 * }).orElseThrow(() -> new ResourceNotFoundException("task list not found"));
		 */
    }

    @PutMapping("/tasklist/{taskListId}/tasks/{taskId}")
    public Task updateTask(@PathVariable(value = "taskListId") Long taskListId,
        @PathVariable(value = "taskId") Long taskId, @Valid @RequestBody Task taskRequest)
    throws ResourceNotFoundException {
        if (!taskListRepository.existsById(taskListId)) {
            throw new ResourceNotFoundException("taskListId not found");
        }

        return taskRepository.findById(taskId).map(task -> {
            task.setTaskTitle(taskRequest.getTaskTitle());
            return taskRepository.save(task);
        }).orElseThrow(() -> new ResourceNotFoundException("task id not found"));
    }

    @DeleteMapping("/tasklist/{taskListId}/tasks/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable(value = "taskListId") Long taskListId,
        @PathVariable(value = "taskId") Long taskId) throws ResourceNotFoundException {
        return taskRepository.findByIdAndTaskListId(taskId, taskListId).map(task -> {
            taskRepository.delete(task);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
            "Task not found with id " + taskId + " and taskListId " + taskListId));
    }
}
