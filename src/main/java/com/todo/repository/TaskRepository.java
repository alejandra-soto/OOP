package com.todo.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.todo.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
	List<Task> findByTaskListId(Long taskListId);
	Optional<Task> findByIdAndTaskListId(Long id, Long taskListId);

}
