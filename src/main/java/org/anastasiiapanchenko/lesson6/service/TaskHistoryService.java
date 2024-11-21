package org.anastasiiapanchenko.lesson6.service;

import org.anastasiiapanchenko.lesson6.dto.TaskHistoryResponseDto;
import org.anastasiiapanchenko.lesson6.dto.TodoUpdateDto;
import org.anastasiiapanchenko.lesson6.mapper.TodoMapper;
import org.anastasiiapanchenko.lesson6.entity.TaskHistory;
import org.anastasiiapanchenko.lesson6.entity.Todo;
import org.anastasiiapanchenko.lesson6.repository.TaskHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service responsible for managing task history.
 * It logs changes to Todo entities and retrieves their history.
 */
@Service
public final class TaskHistoryService {

    /**
     * Repository for managing task history entities.
     */
    private final TaskHistoryRepository taskHistoryRepository;

    /**
     * Mapper to convert Todo entities to TaskHistoryResponseDto.
     */
    private final TodoMapper todoMapper;

    /**
     * Constructor for TaskHistoryService.
     *
     * @param taskHistoryRepositoryParam Repository for managing task
     *                                   history entities.
     * @param todoMapperParam Mapper to convert Todo entities
     *                        to TaskHistoryResponseDto.
     */
    public TaskHistoryService(
            final TaskHistoryRepository taskHistoryRepositoryParam,
            final TodoMapper todoMapperParam) {
        this.taskHistoryRepository = taskHistoryRepositoryParam;
        this.todoMapper = todoMapperParam;
    }

    /**
     * Logs the change from the old Todo
     * to the new Todo along with the user who made the change.
     *
     * @param oldTodo the previous state of the Todo entity
     * @param newTodo the updated state of the Todo entity
     * @param changedBy the user who made the change
     */
    public void logChange(final Todo oldTodo, final TodoUpdateDto newTodo,
                          final String changedBy) {
        TaskHistory taskHistory = new TaskHistory();
        taskHistory.setTodo(oldTodo);
        taskHistory.setOldState(oldTodo.toString());
        taskHistory.setNewState(newTodo.toString());
        taskHistory.setChangeDate(java.time.LocalDateTime.now());
        taskHistory.setChangedBy(Long.valueOf(changedBy));
        taskHistoryRepository.save(taskHistory);
    }

    /**
     * Retrieves the task history for a specific Todo.
     *
     * @param todoId the ID of the Todo to retrieve the history for
     * @return a list of TaskHistoryResponseDto containing the task history
     */
    public List<TaskHistoryResponseDto> getTaskHistory(
            final Long todoId) {
        List<TaskHistory> taskHistories = taskHistoryRepository
                .findByTodoId(todoId);
        return taskHistories.stream()
                .map(todoMapper::toHistoryResponseDto)
                .collect(Collectors.toList());
    }
}
