package org.anastasiiapanchenko.lesson6.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TaskHistoryResponseDto {
    private Long id;
    private Long todoId;
    private String oldState;
    private String newState;
    private LocalDateTime changeDate;
    private String changedBy;
}
