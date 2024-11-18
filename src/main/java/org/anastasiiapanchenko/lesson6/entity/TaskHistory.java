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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id", nullable = false)
    private Todo todo;

    @Column(name = "old_state", columnDefinition = "TEXT")
    private String oldState;

    @Column(name = "new_state", columnDefinition = "TEXT")
    private String newState;

    @CreationTimestamp
    @Column(name = "change_date", nullable = false)
    private LocalDateTime changeDate;

    @Column(name = "changed_by")
    private Long changedBy = 1L;
}

