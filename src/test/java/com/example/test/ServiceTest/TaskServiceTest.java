package com.example.test.ServiceTest;

import com.example.test.Entity.Impl.TaskEntity;
import com.example.test.Entity.Task;
import com.example.test.Model.ModifyTaskModel;
import com.example.test.Model.SimpleTaskModel;
import com.example.test.Model.TaskModel;
import com.example.test.Repository.Impl.DefaultTaskRepository;
import com.example.test.Service.Impl.DefaultTaskService;
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
public class TaskServiceTest {

    @Autowired
    private DefaultTaskService defaultTaskService;

    @MockBean
    private DefaultTaskRepository defaultTaskRepository;

    @Test
    public void createTaskTest() {
        TaskModel task = new TaskModel("title", "description", "time", "status", 1);
        Mockito.when(defaultTaskRepository.createTask(task))
               .thenReturn(true);

        Assertions.assertThat(defaultTaskService.createTask(task))
                  .as("Result is false.")
                  .isTrue();
    }

    @Test
    public void getAllTaskTest() {
        Collection<Task> tasks = new ArrayList<>();
        tasks.add(new TaskEntity(1, "title", "description", "time", "status", 1));
        tasks.add(new TaskEntity(2, "title", "description", "time", "status", 2));
        tasks.add(new TaskEntity(3, "title", "description", "time", "status", 1));
        tasks.add(new TaskEntity(4, "title", "description", "time", "status", 3));
        Mockito.when(defaultTaskRepository.getAllTasks())
               .thenReturn(tasks);

        Collection<SimpleTaskModel> result = defaultTaskService.getAllTask();
        Assertions.assertThat(result)
                  .as("Result is null.")
                  .isNotNull();
        Assertions.assertThat(result)
                  .as("Result is empty.")
                  .isNotEmpty();
    }

    @Test
    public void getWorkerByIdTest() {
        Task task = new TaskEntity(1, "title", "description", "time", "status", 1);
        Mockito.when(defaultTaskRepository.getTaskById(1))
               .thenReturn(Optional.of(task));

        Assertions.assertThat(defaultTaskService.getTaskById(1))
                  .as("Result is null.")
                  .isNotNull();
    }

    @Test
    public void modifyWorkerTest() {
        ModifyTaskModel task = new ModifyTaskModel("title", "description", "time", "status");
        Mockito.when(defaultTaskService.updateTask(1, task))
               .thenReturn(true);

        Assertions.assertThat(defaultTaskService.updateTask(1, task))
                  .as("Result is false.")
                  .isTrue();
    }

    @Test
    public void deleteWorkerByIdTest() {
        Mockito.when(defaultTaskService.deleteTaskById(1))
               .thenReturn(true);

        Assertions.assertThat(defaultTaskService.deleteTaskById(1))
                  .as("Result is false.")
                  .isTrue();
    }

    @Test
    public void assignWorkerToTaskTest() {
        Mockito.when(defaultTaskRepository.assignWorkerToTask(1, 2))
               .thenReturn(true);

        Assertions.assertThat(defaultTaskService.assignWorkerToTask(1, 2))
                  .as("Result is false.")
                  .isTrue();
    }
}
