package org.anastasiiapanchenko.lesson6.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO for representing the task history response.
 * This class contains the details of a task's state changes,
 * including old state, new state,
 * change date, and who made the change.
 */
@Data
@AllArgsConstructor
public class TaskHistoryResponseDto {

    /**
     * Unique identifier for the task history entry.
     */
    private Long id;

    /**
     * Unique identifier for the associated Todo item.
     */
    private Long todoId;

    /**
     * The previous state of the task.
     */
    private String oldState;

    /**
     * The new state of the task after the change.
     */
    private String newState;

    /**
     * The date and time when the task state was changed.
     */
    private LocalDateTime changeDate;

    /**
     * The user who made the change to the task's state.
     */
    private String changedBy;
}
