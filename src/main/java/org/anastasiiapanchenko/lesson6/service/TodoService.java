package org.anastasiiapanchenko.lesson6.service;

import org.anastasiiapanchenko.lesson6.dto.TodoCreateDto;
import org.anastasiiapanchenko.lesson6.dto.TodoResponseDto;
import org.anastasiiapanchenko.lesson6.dto.TodoUpdateDto;
import org.anastasiiapanchenko.lesson6.mapper.TodoMapper;
import org.anastasiiapanchenko.lesson6.entity.Todo;
import org.anastasiiapanchenko.lesson6.repository.TodoRepository;
import org.springframework.stereotype.Service;

/**
 * Service for working with Todo tasks.
 * Provides methods for creating, updating, deleting, and retrieving tasks.
 */
@Service
public class TodoService {

    /**
     * Repository for interacting with the Todo database entities.
     */
    private final TodoRepository todoRepository;

    /**
     * Mapper for converting between Todo entities and DTOs.
     */
    private final TodoMapper todoMapper;

    /**
     * Constructor for initializing TodoService with a repository and mapper.
     *
     * @param todoRepositoryParam Repository for working with Todo entities.
     * @param todoMapperParam Mapper for converting between entities and DTOs.
     */
    public TodoService(final TodoRepository todoRepositoryParam,
                       final TodoMapper todoMapperParam) {
        this.todoRepository = todoRepositoryParam;
        this.todoMapper = todoMapperParam;
    }


    /**
     * Creates a new Todo task.
     *
     * @param todoCreateDto Data for creating the task.
     * @return DTO of the created task.
     */
    public TodoResponseDto createTodo(final TodoCreateDto todoCreateDto) {
        Todo todo = todoMapper.toEntity(todoCreateDto);
        Todo savedTodo = todoRepository.save(todo);
        return todoMapper.toDto(savedTodo);
    }

    /**
     * Updates an existing Todo task.
     *
     * @param id The ID of the task.
     * @param todoUpdateDto Data for updating the task.
     * @return DTO of the updated task.
     */
    public TodoResponseDto updateTodo(final Long id,
                                      final TodoUpdateDto todoUpdateDto) {
        Todo existingTodo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Todo not found with id=" + id));
        Todo updatedTodo = todoMapper.toEntity(todoUpdateDto, existingTodo);
        Todo savedTodo = todoRepository.save(updatedTodo);
        return todoMapper.toDto(savedTodo);
    }

    /**
     * Deletes a Todo task.
     *
     * @param id The ID of the task.
     */
    public void deleteTodo(final Long id) {
        todoRepository.deleteById(id);
    }


    /**
     * Retrieves a Todo task by its ID.
     *
     * @param id The ID of the task.
     * @return The Todo task.
     */
    public Todo getTodoById(final Long id) {
        return todoRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Todo not found with id=" + id));
    }
}
