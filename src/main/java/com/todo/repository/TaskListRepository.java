package com.todo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo.model.TaskList;;

@Repository
public interface TaskListRepository extends JpaRepository<TaskList, Long>{

}
