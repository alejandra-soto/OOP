package com.todo.controller;

import java.util.List;
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
import com.todo.repository.TaskListRepository;
import com.todo.repository.TaskRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController 
@RequestMapping("/todo")
public class TaskController {
	
	@Autowired
    private TaskRepository taskRepository;
	
	@Autowired
    private TaskListRepository taskListRepository;

	@GetMapping("/tasklist/{taskListId}/tasks")
    public List <Task> getAllTasksByTaskList(@PathVariable(value = "taskListId") Long taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }

    @PostMapping("/tasklist/{taskListId}/tasks")
    public Task createTask(@PathVariable(value = "taskListId") Long taskListId,
        @Valid @RequestBody Task task) throws ResourceNotFoundException {
        return taskListRepository.findById(taskListId).map(taskList -> {
            task.setTaskList(taskList);
            return taskRepository.save(task);
        }).orElseThrow(() -> new ResourceNotFoundException("task list not found"));
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
