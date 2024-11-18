package org.anastasiiapanchenko.lesson6;

import org.anastasiiapanchenko.lesson6.repository.TaskHistoryRepository;
import org.anastasiiapanchenko.lesson6.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SpringTest {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TaskHistoryRepository taskHistoryRepository;

    @Test
    void contextLoads() {
        assertNotNull(todoRepository);
        assertNotNull(taskHistoryRepository);
    }
}

