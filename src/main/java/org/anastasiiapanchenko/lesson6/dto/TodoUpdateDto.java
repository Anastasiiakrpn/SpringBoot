package org.anastasiiapanchenko.lesson6.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class TodoUpdateDto {

    @NotBlank
    @Size(max = 100, message = "Title exceeds 100 characters. Need to reduce the number of characters")
    private String title;

    @Size(max = 500, message = "Description exceeds 100 characters. Need to reduce the number of characters")
    private String description;

    @NotNull
    private LocalDateTime dueDate;

    private Priority priority;

    @NotNull
    private Status status;

    public enum Priority {
        LOW, MEDIUM, HIGH
    }

    public enum Status {
        PENDING, IN_PROGRESS, COMPLETED
    }
}
