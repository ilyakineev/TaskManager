package com.example.test.ServiceTest;

import com.example.test.Entity.Impl.TaskEntity;
import com.example.test.Entity.Task;
import com.example.test.Model.ModifyTaskModel;
import com.example.test.Model.SimpleTaskModel;
import com.example.test.Model.TaskModel;
import com.example.test.Repository.Impl.DefaultTaskRepository;
import com.example.test.Service.Impl.DefaultTaskService;
import com.example.test.Service.TaskManager;
import com.example.test.TestContainer;
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

    @MockBean
    private TaskManager taskManager;

    @Test
    public void createTaskTest() {
        Mockito.when(defaultTaskRepository.createTask(TestContainer.TASK_MODEL))
               .thenReturn(true);

        Assertions.assertThat(defaultTaskService.createTask(TestContainer.TASK_MODEL))
                  .as("Result is false.")
                  .isTrue();
    }

    @Test
    public void getAllTaskTest() {
        Mockito.when(defaultTaskRepository.getAllTasks())
               .thenReturn(TestContainer.TASKS_ENTITY);

        Collection<SimpleTaskModel> result = defaultTaskService.getAllTask();
        Assertions.assertThat(result)
                  .as("Result is null.")
                  .isNotNull();
        Assertions.assertThat(result)
                  .as("Result is empty.")
                  .isNotEmpty();
    }

    @Test
    public void getTaskByIdTest() {
        Task task = new TaskEntity(1, "title", "description", "time", "status", 1);
        Mockito.when(defaultTaskRepository.getTaskById(1))
               .thenReturn(Optional.of(task));

        Assertions.assertThat(defaultTaskService.getTaskById(1))
                  .as("Result is null.")
                  .isNotNull();
    }

    @Test
    public void modifyTaskTest() {
        ModifyTaskModel task = new ModifyTaskModel("title", "description", "time", "status");
        Mockito.when(defaultTaskService.updateTask(1, task))
               .thenReturn(true);

        Assertions.assertThat(defaultTaskService.updateTask(1, task))
                  .as("Result is false.")
                  .isTrue();
    }

    @Test
    public void deleteTaskByIdTest() {
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

//    @Test
//    public void addTaskInPipelineTest() {
//        TaskModel task = new TaskModel("title", "description", "time", "status", 1);
//
//        Mockito.when(taskManager.addTaskInPipeline(task))
//               .thenReturn(true);
//        Assertions.assertThat(defaultTaskService.addTaskInPipeline(1))
//                  .as("Result is false.")
//                  .isTrue();
//    }
//
//    @Test
//    public void addTasksInPipelineTest() {
//        TaskModel task = new TaskModel("title", "description", "time", "status", 1);
//
//        Mockito.when(taskManager.addTaskInPipeline(task))
//               .thenReturn(true);
//        Assertions.assertThat(defaultTaskService.addTaskInPipeline(1))
//                  .as("Result is false.")
//                  .isTrue();
//    }

    @Test
    public void getStatusWorkTest() {
        Mockito.when(taskManager.getStatusTask())
               .thenReturn(TestContainer.TASKS_MODEL);
        final Collection<TaskModel> result = defaultTaskService.getStatusTask();
        Assertions.assertThat(result)
                  .as("Result is null.")
                  .isNotNull();
        Assertions.assertThat(result)
                  .as("Result is empty.")
                  .isNotEmpty();
        Assertions.assertThat(result)
                  .as("Result is incorrect.")
                  .containsAll(TestContainer.TASKS_MODEL);
    }
}
