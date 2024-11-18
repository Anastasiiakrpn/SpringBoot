package org.anastasiiapanchenko.lesson6.repository;

import org.anastasiiapanchenko.lesson6.entity.TaskHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskHistoryRepository extends JpaRepository<TaskHistory, Long> {
    List<TaskHistory> findByTodoId(Long todoId);
}
