package com.todo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.todo.model.TaskList;;

@Repository
//@CrossOrigin(origins = "http://localhost:4200")
public interface TaskListRepository extends JpaRepository<TaskList, Long>{

}
