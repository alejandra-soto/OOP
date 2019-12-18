package com.todo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

//@CrossOrigin(origins = "http://localhost:4200")
@RestController 
@RequestMapping("/")
public class TaskListController {

    @Autowired
    private TaskListRepository taskListRepository;


    @GetMapping("/tasklist")
    public List <TaskList> getAllTaskLists() {
    	return taskListRepository.findAll();
    	/*System.out.println("In Getting All Task Lists");
    	List <TaskList> tasks = taskListRepository.findAll();
    	
    	List<TaskList> tasksNew = new ArrayList<TaskList>();
    	Iterator<TaskList> tlItr = tasks.iterator();
    	while(tlItr.hasNext()) {
    		TaskList tl = tlItr.next();
    		TaskList tl1 = new TaskList();
    		tl1.setId(tl.getId());
    		tl1.setListTitle(tl.getListTitle());
    		List<Task> newTasks = new ArrayList<Task>();
    		Iterator<Task> itr = tl.getTasks().iterator();
    		while(itr.hasNext()) {
    			Task t = itr.next();
    			Task newT = new Task();
    			newT.setId(t.getId());
    			newT.setTaskTitle(t.getTaskTitle());
    			newT.setTaskList(null);
    			newTasks.add(newT);
     		}
    		tl1.setTasks(newTasks);
    		tasksNew.add(tl1);
    	}
        return tasksNew;*/
    }

    @GetMapping("/tasklist/{id}")
    public ResponseEntity <TaskList> getTaskListById(
        @PathVariable(value = "id") Long taskListId) throws ResourceNotFoundException {
        
    	System.out.println("In Getting Task List by ID");
    	TaskList taskList = taskListRepository.findById(taskListId)
            .orElseThrow(() -> new ResourceNotFoundException("Task list not found :: " + taskListId));
        return ResponseEntity.ok().body(taskList);
    }

    @PostMapping("/tasklist")
    public TaskList createTaskList(@Valid @RequestBody TaskList taskList) {
    	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    	System.out.println("In Creating Task List");
        return taskListRepository.save(taskList);
    }

    @PutMapping("/tasklist/{id}")
    public ResponseEntity <TaskList> updateTaskList(
        @PathVariable(value = "id") Long taskListId,
        @Valid @RequestBody TaskList taskListDetails) throws ResourceNotFoundException {
        TaskList taskList = taskListRepository.findById(taskListId)
            .orElseThrow(() -> new ResourceNotFoundException("Task list not found :: " + taskListId));
        taskList.setListTitle(taskListDetails.getListTitle());
        final TaskList updatedTaskList = taskListRepository.save(taskList);
        return ResponseEntity.ok(updatedTaskList);
    }

    @DeleteMapping("/tasklist/{id}")
    public Map <String, Boolean> deleteTaskList(@PathVariable(value = "id") Long taskListId) 
    		throws ResourceNotFoundException {
        TaskList taskList = taskListRepository.findById(taskListId)
            .orElseThrow(() -> new ResourceNotFoundException("Task list not found :: " + taskListId));
        taskListRepository.delete(taskList);
        Map <String, Boolean> response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
	
}
