package org.anastasiiapanchenko.lesson6.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@SQLDelete(sql = "UPDATE task_history SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
@Table(name = "task_history")
public class TaskHistory {

    /**
     * The unique identifier for the TaskHistory entry.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The associated Todo task for this history entry.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id", nullable = false)
    private Todo todo;

    /**
     * The previous state of the task before the change.
     */
    @Column(name = "old_state", columnDefinition = "TEXT")
    private String oldState;

    /**
     * The new state of the task after the change.
     */
    @Column(name = "new_state", columnDefinition = "TEXT")
    private String newState;

    /**
     * The timestamp of when the change was made.
     */
    @CreationTimestamp
    @Column(name = "change_date", nullable = false)
    private LocalDateTime changeDate;

    /**
     * The ID of the user who made the change.
     */
    @Column(name = "changed_by")
    private Long changedBy = 1L;
}

