package org.anastasiiapanchenko.lesson6.service;

import org.anastasiiapanchenko.lesson6.dto.TodoCreateDto;
import org.anastasiiapanchenko.lesson6.dto.TodoResponseDto;
import org.anastasiiapanchenko.lesson6.dto.TodoUpdateDto;
import org.anastasiiapanchenko.lesson6.mapper.TodoMapper;
import org.anastasiiapanchenko.lesson6.entity.Todo;
import org.anastasiiapanchenko.lesson6.repository.TodoRepository;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;

    public TodoService(TodoRepository todoRepository, TodoMapper todoMapper) {
        this.todoRepository = todoRepository;
        this.todoMapper = todoMapper;
    }

    // Создание нового Todo
    public TodoResponseDto createTodo(TodoCreateDto todoCreateDto) {
        Todo todo = todoMapper.toEntity(todoCreateDto);
        Todo savedTodo = todoRepository.save(todo);
        return todoMapper.toDto(savedTodo);  // Используем toDto вместо toResponseDto
    }

    // Обновление существующего Todo
    public TodoResponseDto updateTodo(Long id, TodoUpdateDto todoUpdateDto) {
        Todo existingTodo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));
        Todo updatedTodo = todoMapper.toEntity(todoUpdateDto, existingTodo);
        Todo savedTodo = todoRepository.save(updatedTodo);
        return todoMapper.toDto(savedTodo);  // Используем toDto вместо toResponseDto
    }

    // Удаление Todo
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }

    // Получение Todo по ID
    public Todo getTodoById(Long id) {
        return todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Todo not found"));
    }
}
