package org.anastasiiapanchenko.lesson6.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

/**
 * DTO for creating a Todo item.
 */
public class TodoCreateDto {

    /** Maximum length for the title. */
    private static final int MAX_TITLE_LENGTH = 100;

    /** Maximum length for the description. */
    private static final int MAX_DESCRIPTION_LENGTH = 500;

    /**
     * Title of the Todo item.
     * It must not be blank and cannot exceed
     * {@link #MAX_TITLE_LENGTH} characters.
     */

    @NotBlank
    @Size(max = MAX_TITLE_LENGTH,
            message = "Title exceeds " + MAX_TITLE_LENGTH
                    + " characters. Need to reduce the number of characters")
    private String title;

    /**
     * Description of the Todo item.
     * Cannot exceed {@link #MAX_DESCRIPTION_LENGTH} characters.
     */
    @Size(max = MAX_DESCRIPTION_LENGTH,
            message = "Description exceeds " + MAX_DESCRIPTION_LENGTH
                    + " characters. Need to reduce the number of characters.")
    private String description;

    /**
     * Due date of the Todo item.
     */
    @NotNull
    private LocalDateTime dueDate;

    /**
     * Priority of the Todo item.
     */
    @NotNull
    private Priority priority;

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
}

