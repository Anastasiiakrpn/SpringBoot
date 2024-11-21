package org.anastasiiapanchenko.lesson6.repository;

import org.anastasiiapanchenko.lesson6.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
