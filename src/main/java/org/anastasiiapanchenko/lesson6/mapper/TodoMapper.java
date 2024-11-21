package org.anastasiiapanchenko.lesson6.mapper;

import org.anastasiiapanchenko.lesson6.dto.TaskHistoryResponseDto;
import org.anastasiiapanchenko.lesson6.dto.TodoCreateDto;
import org.anastasiiapanchenko.lesson6.dto.TodoResponseDto;
import org.anastasiiapanchenko.lesson6.dto.TodoUpdateDto;
import org.anastasiiapanchenko.lesson6.entity.TaskHistory;
import org.anastasiiapanchenko.lesson6.entity.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.anastasiiapanchenko.lesson6.config.MapperConfig;

/**
 * Mapper interface for converting between Todo entity and its DTOs.
 */
@Mapper(config = MapperConfig.class)
public interface TodoMapper {

    /**
     * Converts a Todo entity to a TodoResponseDto.
     *
     * @param todo The Todo entity to convert.
     * @return The corresponding TodoResponseDto.
     */
    TodoResponseDto toDto(Todo todo);

    /**
     * Converts a TodoCreateDto to a Todo entity.
     *
     * @param dto The TodoCreateDto to convert.
     * @return The corresponding Todo entity.
     */
    Todo toEntity(TodoCreateDto dto);

    /**
     * Converts a TodoUpdateDto to a Todo entity,
     * updating an existing Todo entity.
     *
     * @param dto The TodoUpdateDto to convert.
     * @param existingTodo The existing Todo entity to update.
     * @return The updated Todo entity.
     */
    Todo toEntity(TodoUpdateDto dto, Todo existingTodo);

    /**
     * Converts a string representation of priority
     * to the corresponding Todo.Priority enum.
     * Defaults to LOW if the string is null or does not match any priority.
     *
     * @param priority The string representation of the priority.
     * @return The corresponding Todo.Priority enum.
     */
    @Named("stringToPriority")
    default Todo.Priority stringToPriority(String priority) {
        if (priority == null) {
            return Todo.Priority.LOW;
        }
        return Todo.Priority.valueOf(priority.toUpperCase());
    }

    /**
     * Converts a TaskHistory entity to a TaskHistoryResponseDto.
     *
     * @param taskHistory The TaskHistory entity to convert.
     * @return The corresponding TaskHistoryResponseDto.
     */
    TaskHistoryResponseDto toHistoryResponseDto(TaskHistory taskHistory);

}
