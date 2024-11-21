package org.anastasiiapanchenko.lesson6.repository;

import org.anastasiiapanchenko.lesson6.entity.TaskHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for managing
 * {@link TaskHistory} entities.
 */
public interface TaskHistoryRepository
        extends JpaRepository<TaskHistory, Long> {
    /**
     * Retrieves a list of task history entries
     * associated with a specific todo item.
     *
     * @param todoId the ID of the todo item
     * @return a list of task history entries linked to the specified todo item
     */
    List<TaskHistory> findByTodoId(Long todoId);
}
