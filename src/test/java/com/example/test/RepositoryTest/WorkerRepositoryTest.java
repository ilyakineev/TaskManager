package com.example.test.RepositoryTest;

import com.example.test.Model.WorkerModel;
import com.example.test.Repository.Impl.DefaultWorkerRepository;
import com.example.test.Service.Impl.DefaultWorkerService;
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
public class WorkerRepositoryTest {

    @Autowired
    private DefaultWorkerService defaultWorkerService;

    @MockBean
    private DefaultWorkerRepository defaultWorkerRepository;

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
        Assertions.assertThat(result)
                  .as("Result is null.")
                  .containsAll(TestContainer.WORKERS);
    }

    @Test
    public void getWorkerByIdTest() {
        Mockito.when(defaultWorkerRepository.getWorkerById(1))
               .thenReturn(Optional.of(TestContainer.WORKER_ENTITY));
        WorkerModel result = defaultWorkerService.getWorkerById(1);
        Assertions.assertThat(result)
                  .as("Result is null.")
                  .isNotNull();
        Assertions.assertThat(result)
                  .as("Result is incorrect.")
                  .isEqualTo(TestContainer.WORKER_MODEL);
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
        Mockito.when(defaultWorkerRepository.deleteWorkerById(2))
               .thenReturn(true);
        Assertions.assertThat(defaultWorkerService.deleteWorkerById(2))
                  .as("Result is false.")
                  .isTrue();
    }
}
