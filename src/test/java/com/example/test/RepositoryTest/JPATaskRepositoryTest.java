package com.example.test.RepositoryTest;

import com.example.test.Entity.Task;
import com.example.test.Repository.Impl.JPATaskRepository;
import com.example.test.TestContainer;
import java.util.Collection;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class JPATaskRepositoryTest {

    @Autowired
    private JPATaskRepository defaultTaskRepository;

    @Test
    public void createTaskTest() {
        Assertions.assertThat(defaultTaskRepository.createTask(TestContainer.TASK_MODEL))
                  .as("Result is false.")
                  .isTrue();
    }

    @Test
    public void getAllTaskTest() {
        Collection<Task> result = defaultTaskRepository.getAllTasks();
        Assertions.assertThat(result)
                  .as("Result is null.")
                  .isNotNull();
        Assertions.assertThat(result)
                  .as("Result is empty.")
                  .isNotEmpty();
    }

    @Test
    public void getTaskByIdTest() {
        Assertions.assertThat(defaultTaskRepository.getTaskById(1).orElse(null))
                  .as("Result is false.")
                  .isNotNull();
    }

    @Test
    public void modifyTaskTest() {
        Assertions.assertThat(defaultTaskRepository.modifyTask(3, TestContainer.MODIFY_TASK_MODEL))
                  .as("Result is false.")
                  .isTrue();
    }

    @Test
    public void deleteTaskByIdTest() {
        Assertions.assertThat(defaultTaskRepository.deleteTaskById(2))
                  .as("Result is false.")
                  .isTrue();
    }

    @Test
    public void assignTaskToTaskTest() {
        Assertions.assertThat(defaultTaskRepository.assignWorkerToTask(3, 3))
                  .as("Result is false.")
                  .isTrue();
    }
}
