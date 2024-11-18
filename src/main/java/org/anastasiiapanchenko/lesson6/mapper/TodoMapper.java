package org.anastasiiapanchenko.lesson6.mapper;

import org.anastasiiapanchenko.lesson6.dto.TaskHistoryResponseDto;
import org.anastasiiapanchenko.lesson6.dto.TodoCreateDto;
import org.anastasiiapanchenko.lesson6.dto.TodoResponseDto;
import org.anastasiiapanchenko.lesson6.dto.TodoUpdateDto;
import org.anastasiiapanchenko.lesson6.entity.TaskHistory;
import org.anastasiiapanchenko.lesson6.entity.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.anastasiiapanchenko.lesson6.config.MapperConfig;

@Mapper(config = MapperConfig.class)
public interface TodoMapper {

    TodoResponseDto toDto(Todo todo);

    Todo toEntity(TodoCreateDto dto);

    Todo toEntity(TodoUpdateDto dto, Todo existingTodo);

    // Кастомный метод для преобразования строки в перечисление
    @Named("stringToPriority")
    default Todo.Priority stringToPriority(String priority) {
        if (priority == null) {
            return Todo.Priority.LOW; // Значение по умолчанию
        }
        return Todo.Priority.valueOf(priority.toUpperCase()); // Преобразуем строку в Enum
    }

    TaskHistoryResponseDto toHistoryResponseDto(TaskHistory taskHistory);

}