package com.example.test.ServiceTest;

import com.example.test.Model.WorkerModel;
import com.example.test.Repository.Impl.JDBCWorkerRepository;
import com.example.test.Repository.Impl.JPAWorkerRepository;
import com.example.test.Service.Impl.DefaultWorkerService;
import com.example.test.Service.TaskLoader;
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
public class WorkerServiceTest {

    @Autowired
    private DefaultWorkerService defaultWorkerService;

    @MockBean
    private JPAWorkerRepository defaultWorkerRepository;

    @MockBean
    private TaskLoader taskLoader;

    @Test
    public void createWorkerTest() {
        Mockito.when(defaultWorkerRepository.createWorker(TestContainer.WORKER_MODEL))
               .thenReturn(true);

        Assertions.assertThat(defaultWorkerService.createWorker(TestContainer.WORKER_MODEL))
                  .as("Result is false.")
                  .isTrue();
    }

    @Test
    public void getAllWorkerTest() {
        Mockito.when(defaultWorkerRepository.getAllWorkers())
               .thenReturn(TestContainer.WORKERS_ENTITY);

        final Collection<WorkerModel> result = defaultWorkerService.getAllWorkers();
        Assertions.assertThat(result)
                  .as("Result is null.")
                  .isNotNull();
        Assertions.assertThat(result)
                  .as("Result is empty.")
                  .isNotEmpty();
    }

    @Test
    public void getWorkerByIdTest() {
        Mockito.when(defaultWorkerRepository.getWorkerById(1))
               .thenReturn(Optional.of(TestContainer.WORKER_ENTITY));

        Assertions.assertThat(defaultWorkerService.getWorkerById(1))
                  .as("Result is false.")
                  .isNotNull();
    }

    @Test
    public void modifyWorkerTest() {
        Mockito.when(defaultWorkerRepository.modifyWorker(1, TestContainer.WORKER_MODEL))
               .thenReturn(true);

        Assertions.assertThat(defaultWorkerService.modifyWorker(1, TestContainer.WORKER_MODEL))
                  .as("Result is false.")
                  .isTrue();
    }

    @Test
    public void deleteWorkerByIdTest() {
        Mockito.when(defaultWorkerService.deleteWorkerById(1))
               .thenReturn(true);

        Assertions.assertThat(defaultWorkerService.deleteWorkerById(1))
                  .as("Result is false.")
                  .isTrue();
    }

//    @Test
//    public void addTaskInPipelineTest() {
//        WorkerModel worker = new WorkerModel("name", "position", "avatar");
//
//        Mockito.when(taskLoader.addTaskInPipeline(worker))
//               .thenReturn(true);
//        Assertions.assertThat(defaultWorkerService.addTaskInPipeline(1))
//                  .as("Result is false.")
//                  .isTrue();
//    }
//
//    @Test
//    public void removeWorkerInPipelineTest() {
//        WorkerModel worker = new WorkerModel("name", "position", "avatar");
//
//        Mockito.when(taskLoader.removeWorkerInPipeline(worker))
//               .thenReturn(true);
//        Assertions.assertThat(defaultWorkerService.removeWorkerInPipeline(1))
//                  .as("Result is false.")
//                  .isTrue();
//    }

    @Test
    public void getStatusWorkTest() {
        Mockito.when(taskLoader.getStatusWork())
               .thenReturn(TestContainer.WORKERS);

        final Collection<WorkerModel> result = defaultWorkerService.getStatusWorkers();
        Assertions.assertThat(result)
                  .as("Result is null.")
                  .isNotNull();
        Assertions.assertThat(result)
                  .as("Result is empty.")
                  .isNotEmpty();
        Assertions.assertThat(result)
                  .as("Result is incorrect.")
                  .containsAll(TestContainer.WORKERS);
    }
}
