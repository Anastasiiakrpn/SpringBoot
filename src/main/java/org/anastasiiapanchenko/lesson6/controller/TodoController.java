package org.anastasiiapanchenko.lesson6.controller;

import lombok.RequiredArgsConstructor;
import org.anastasiiapanchenko.lesson6.dto.TaskHistoryResponseDto;
import org.anastasiiapanchenko.lesson6.dto.TodoCreateDto;
import org.anastasiiapanchenko.lesson6.dto.TodoResponseDto;
import org.anastasiiapanchenko.lesson6.dto.TodoUpdateDto;
import org.anastasiiapanchenko.lesson6.entity.Todo;
import org.anastasiiapanchenko.lesson6.service.TaskHistoryService;
import org.anastasiiapanchenko.lesson6.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.validation.Valid;
import java.util.List;

/**
 * Controller for managing Todo tasks.
 * Provides endpoints for creating,
 * updating, deleting Todo tasks and retrieving task history.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/todos")
public class TodoController {
    /**
     * Service responsible for managing Todo tasks.
     */
    private final TodoService todoService;

    /**
     * Service responsible for managing task history.
     */
    private final TaskHistoryService taskHistoryService;

    /**
     * Creates a new Todo task.
     *
     * @param todoCreateDto the DTO containing
     *                      the details of the Todo task to create.
     * @return ResponseEntity with the created Todo task.
     */
    @PostMapping
    public ResponseEntity<TodoResponseDto> createTodo(
            @Valid @RequestBody final TodoCreateDto todoCreateDto) {
        TodoResponseDto createdTodo = todoService.createTodo(todoCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTodo);
    }

    /**
     * Updates an existing Todo task.
     *
     * @param id the ID of the Todo task to update.
     * @param todoUpdateDto the DTO containing
     *                      the updated details for the Todo task.
     * @return the updated Todo task.
     */
    @PutMapping("/{id}")
    public TodoResponseDto updateTodo(
            @PathVariable final Long id,
            @Valid @RequestBody final TodoUpdateDto todoUpdateDto) {
        TodoResponseDto updatedTodo = todoService.updateTodo(id, todoUpdateDto);
        Todo oldTodo = todoService.getTodoById(id);
        taskHistoryService.logChange(oldTodo, todoUpdateDto, "system");
        return updatedTodo;
    }

    /**
     * Deletes a Todo task.
     *
     * @param id the ID of the Todo task to delete.
     * @return ResponseEntity indicating the result of the deletion.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable final Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves the history of changes for a specific Todo task.
     *
     * @param id the ID of the Todo task for which to retrieve the history.
     * @return a list of TaskHistoryResponseDto
     * objects representing the change history.
     */
    @GetMapping("/{id}/history")
    public List<TaskHistoryResponseDto> getTaskHistory(
            @PathVariable final Long id) {
        List<TaskHistoryResponseDto> taskHistory =
                taskHistoryService.getTaskHistory(id);
        return taskHistory;
    }
}
