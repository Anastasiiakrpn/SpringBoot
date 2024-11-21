package org.anastasiiapanchenko.lesson6.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a Todo item.
 */
@Data
@Entity
@Table(name = "todos")
@SQLDelete(sql = "UPDATE todos SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
public class Todo {

    /**
     * The unique identifier of the todo.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The title of the todo item.
     */
    private String title;

    /**
     * A description of the todo item.
     */
    private String description;

    /**
     * The due date of the todo item.
     */
    @Column(name = "due_date")
    private LocalDateTime dueDate;

    /**
     * The priority of the todo item (LOW, MEDIUM, HIGH).
     */
    @Enumerated(EnumType.STRING)
    private Priority priority;

    /**
     * The current status of the todo item (PENDING, IN_PROGRESS, COMPLETED).
     */
    @Enumerated(EnumType.STRING)
    private Status status;

    /**
     * The timestamp when the todo was created.
     */
    @CreationTimestamp
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    /**
     * The timestamp when the todo was last updated.
     */
    @UpdateTimestamp
    @Column(name = "updated_date", nullable = false)
    private LocalDateTime updatedDate;

    /**
     * The ID of the user associated with the todo.
     */
    @Column(name = "user_id")
    private Long userId = 1L;

    /**
     * Flag indicating if the todo has been deleted.
     */
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    /**
     * A list of task history entries associated with the todo.
     */
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "todo",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskHistory> taskHistories = new ArrayList<>();

    /**
     * Retrieves formatted information about the todo.
     *
     * @return a formatted string containing details about the todo.
     */
    public String getInfo() {
        return String.format("Title: %s Description:"
                        + " %s Due date: %s Priority: %s Status: %s",
                title, description, dueDate, priority, status);
    }

    /**
     * Enum representing the priority levels of a todo item.
     */
    public enum Priority {
        /** Task with low priority. */
        LOW,
        /** Task with medium priority. */
        MEDIUM,
        /** Task with high priority. */
        HIGH
    }

    /**
     * Enum representing the possible statuses of a todo item.
     */
    public enum Status {

        /** Task is pending and has not been started yet. */
        PENDING,
        /** Task is currently in progress. */
        IN_PROGRESS,
        /** Task has been completed. */
        COMPLETED
    }

    /**
     * Marks the todo item as deleted.
     */
    public void markAsDeleted() {
        this.isDeleted = true;
    }

    /**
     * Restores the todo item by unmarking it as deleted.
     */
    public void restore() {
        this.isDeleted = false;
    }
}
