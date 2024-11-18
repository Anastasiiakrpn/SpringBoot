package org.anastasiiapanchenko.lesson6.controller;

import lombok.RequiredArgsConstructor;
import org.anastasiiapanchenko.lesson6.dto.TaskHistoryResponseDto;
import org.anastasiiapanchenko.lesson6.dto.TodoCreateDto;
import org.anastasiiapanchenko.lesson6.dto.TodoResponseDto;
import org.anastasiiapanchenko.lesson6.dto.TodoUpdateDto;
import org.anastasiiapanchenko.lesson6.entity.Todo;
import org.anastasiiapanchenko.lesson6.service.TaskHistoryService;
import org.anastasiiapanchenko.lesson6.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;
    private final TaskHistoryService taskHistoryService;

    @PostMapping
    public ResponseEntity<TodoResponseDto> createTodo(@Valid @RequestBody TodoCreateDto todoCreateDto) {
        TodoResponseDto createdTodo = todoService.createTodo(todoCreateDto);
        return ResponseEntity.status(201).body(createdTodo); // Status 201 (Created)
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoResponseDto> updateTodo(
            @PathVariable Long id,
            @Valid @RequestBody TodoUpdateDto todoUpdateDto) {
        TodoResponseDto updatedTodo = todoService.updateTodo(id, todoUpdateDto);
        Todo oldTodo = todoService.getTodoById(id);
        taskHistoryService.logChange(oldTodo, todoUpdateDto, "system"); // Пример изменения
        return ResponseEntity.ok(updatedTodo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/history")
    public ResponseEntity<List<TaskHistoryResponseDto>> getTaskHistory(@PathVariable Long id) {
        List<TaskHistoryResponseDto> taskHistory = taskHistoryService.getTaskHistory(id);
        return ResponseEntity.ok(taskHistory);
    }
}