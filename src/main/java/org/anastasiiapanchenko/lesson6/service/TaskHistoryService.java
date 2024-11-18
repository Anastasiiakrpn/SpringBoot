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

@Service
public class TaskHistoryService {

    private final TaskHistoryRepository taskHistoryRepository;
    private final TodoMapper todoMapper;

    public TaskHistoryService(TaskHistoryRepository taskHistoryRepository, TodoMapper todoMapper) {
        this.taskHistoryRepository = taskHistoryRepository;
        this.todoMapper = todoMapper;
    }

    // Логирование изменений в TaskHistory
    public void logChange(Todo oldTodo, TodoUpdateDto newTodo, String changedBy) {
        TaskHistory taskHistory = new TaskHistory();
        taskHistory.setTodo(oldTodo);
        taskHistory.setOldState(oldTodo.toString()); // Вы можете заменить это на JSON, если требуется
        taskHistory.setNewState(newTodo.toString()); // Вы можете заменить это на JSON, если требуется
        taskHistory.setChangeDate(java.time.LocalDateTime.now());
        taskHistory.setChangedBy(Long.valueOf(changedBy));
        taskHistoryRepository.save(taskHistory);
    }

    // Получение истории задач для Todo
    public List<TaskHistoryResponseDto> getTaskHistory(Long todoId) {
        List<TaskHistory> taskHistories = taskHistoryRepository.findByTodoId(todoId);
        return taskHistories.stream()
                .map(todoMapper::toHistoryResponseDto)
                .collect(Collectors.toList());
    }
}
