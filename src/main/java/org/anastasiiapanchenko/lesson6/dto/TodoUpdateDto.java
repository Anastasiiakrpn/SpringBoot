package org.anastasiiapanchenko.lesson6.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

/**
 * DTO for updating the details of a Todo item.
 * This class includes validation constraints
 * to ensure proper data input.
 */
public class TodoUpdateDto {

    /**
     * Maximum allowed length for the title.
     */
    private static final int MAX_TITLE_LENGTH = 100;

    /**
     * Maximum allowed length for the description.
     */
    private static final int MAX_DESCRIPTION_LENGTH = 500;

    /**
     * Title of the Todo item.
     * Cannot be blank and must not exceed 100 characters.
     */
    @NotBlank
    @Size(max = MAX_TITLE_LENGTH, message = "Title exceeds " + MAX_TITLE_LENGTH
            + " characters. Please reduce the number of characters.")
    private String title;

    /**
     * Description of the Todo item. Must not exceed 500 characters.
     */
    @Size(max = MAX_DESCRIPTION_LENGTH,
            message = "Description exceeds " + MAX_DESCRIPTION_LENGTH
            + " characters. Need to reduce the number of characters")
    private String description;

    /**
     * Due date for the Todo item. Must not be null.
     */
    @NotNull
    private LocalDateTime dueDate;

    /**
     * Priority of the Todo item. Can be LOW, MEDIUM, or HIGH.
     */
    private Priority priority;

    /**
     * Status of the Todo item.
     * Cannot be null and must be one of PENDING,
     * IN_PROGRESS, or COMPLETED.
     */
    @NotNull
    private Status status;

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
