package org.anastasiiapanchenko.lesson6.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * DTO for representing the details of a Todo item in the response.
 * This class includes information such as title, description,
 * due date, priority, status, timestamps,
 * and the associated user ID.
 */
@Data
@AllArgsConstructor
public class TodoResponseDto {
    /**
     * Unique identifier of the Todo item.
     */
    private Long id;

    /**
     * Title of the Todo item.
     */
    private String title;

    /**
     * Description of the Todo item.
     */
    private String description;

    /**
     * Due date of the Todo item.
     */
    private LocalDateTime dueDate;

    /**
     * Priority level of the Todo item.
     */
    private Priority priority;

    /**
     * Current status of the Todo item.
     */
    private Status status;

    /**
     * The date and time when the Todo item was created.
     */
    private LocalDateTime createdDate;

    /**
     * The date and time when the Todo item was last updated.
     */
    private LocalDateTime updatedDate;

    /**
     * ID of the user associated with the Todo item.
     */
    private Long userId;

    /**
     * Enum representing the priority levels of a Todo item.
     */
    public enum Priority {
        /**
         * Low priority level.
         */
        LOW,

        /**
         * Medium priority level.
         */
        MEDIUM,

        /**
         * High priority level.
         */
        HIGH
    }

    /**
     * Enum representing the status of a Todo item.
     */
    public enum Status {
        /**
         * Todo item is pending and not yet started.
         */
        PENDING,

        /**
         * Todo item is currently in progress.
         */
        IN_PROGRESS,

        /**
         * Todo item has been completed.
         */
        COMPLETED
    }
}
