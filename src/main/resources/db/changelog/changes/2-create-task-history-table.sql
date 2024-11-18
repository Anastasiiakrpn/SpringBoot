--liquibase formatted sql
--changeset anastasiiapanchenko:2-create-task-history-table
CREATE TABLE task_history (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    todo_id BIGINT NOT NULL,
    old_state VARCHAR(500) NOT NULL,
    new_state VARCHAR(500) NOT NULL,
    change_date TIMESTAMP NOT NULL,
    changed_by VARCHAR(100),
    FOREIGN KEY (todo_id) REFERENCES todos(id)
);

--rollback DROP TABLE task_history;
