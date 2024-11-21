--liquibase formatted sql
--changeset anastasiiapanchenko:1-create-todos-table
CREATE TABLE IF NOT EXISTS todos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    due_date TIMESTAMP NOT NULL,
    priority VARCHAR(20),
    status VARCHAR(20) NOT NULL,
    created_date TIMESTAMP NOT NULL,
    updated_date TIMESTAMP,
    user_id BIGINT NOT NULL
);
--rollback DROP TABLE todos;
