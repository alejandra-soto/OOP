package com.todo.controller;

import java.util.HashMap;
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
import com.todo.model.TaskList;
import com.todo.repository.TaskListRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController 
@RequestMapping("/")
public class TaskListController {

    @Autowired
    private TaskListRepository taskListRepository;


    @GetMapping("/tasklist")
    public List <TaskList> getAllTaskLists() {
        return taskListRepository.findAll();
    }

    @GetMapping("/tasklist/{id}")
    public ResponseEntity <TaskList> getTaskListById(
        @PathVariable(value = "id") Long taskListId) throws ResourceNotFoundException {
        TaskList taskList = taskListRepository.findById(taskListId)
            .orElseThrow(() -> new ResourceNotFoundException("Task list not found :: " + taskListId));
        return ResponseEntity.ok().body(taskList);
    }

    @PostMapping("/tasklist")
    public TaskList createTaskList(@Valid @RequestBody TaskList taskList) {
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
