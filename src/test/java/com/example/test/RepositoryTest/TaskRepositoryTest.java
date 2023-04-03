package com.example.test.RepositoryTest;

import com.example.test.Entity.Impl.TaskEntity;
import com.example.test.Entity.Task;
import com.example.test.Model.ModifyTaskModel;
import com.example.test.Model.TaskModel;
import com.example.test.Repository.Impl.DefaultTaskRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskRepositoryTest {

    @Autowired
    private DefaultTaskRepository defaultTaskRepository;

    @Test
    public void createWorkerTest() {
        TaskModel task = new TaskModel("title", "description", "time", "status", 3);

        Assertions.assertThat(defaultTaskRepository.createTask(task))
                  .as("Result is false.")
                  .isTrue();
    }

    @Test
    public void getAllWorkerTest() {
        Collection<Task> result = defaultTaskRepository.getAllTasks();
        Assertions.assertThat(result)
                  .as("Result is null.")
                  .isNotNull();
        Assertions.assertThat(result)
                  .as("Result is empty.")
                  .isNotEmpty();
    }

    @Test
    public void getWorkerByIdTest() {
        Assertions.assertThat(defaultTaskRepository.getTaskById(2).orElse(null))
                  .as("Result is false.")
                  .isNotNull();
    }

    @Test
    public void modifyWorkerTest() {
        ModifyTaskModel task = new ModifyTaskModel("title", "description", "time", "status");
        Assertions.assertThat(defaultTaskRepository.modifyTask(3, task))
                  .as("Result is false.")
                  .isTrue();
    }

//    @Test
//    public void deleteWorkerByIdTest() {
//        Assertions.assertThat(defaultTaskRepository.deleteTaskById(2))
//                  .as("Result is false.")
//                  .isTrue();
//    }

    @Test
    public void assignWorkerToTaskTest() {
        Assertions.assertThat(defaultTaskRepository.assignWorkerToTask(3, 3))
                  .as("Result is false.")
                  .isTrue();
    }
}
