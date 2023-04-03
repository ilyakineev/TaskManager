package com.example.test.ServiceTest;

import com.example.test.Entity.Impl.WorkerEntity;
import com.example.test.Entity.Worker;
import com.example.test.Model.WorkerModel;
import com.example.test.Repository.Impl.DefaultWorkerRepository;
import com.example.test.Service.Impl.DefaultWorkerService;
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
public class WorkerServiceTest {

    @Autowired
    private DefaultWorkerService defaultWorkerService;

    @MockBean
    private DefaultWorkerRepository defaultWorkerRepository;

    @Test
    public void createWorkerTest() {
        WorkerModel worker = new WorkerModel("name", "position", "avatar");
        Mockito.when(defaultWorkerRepository.createWorker(worker)).thenReturn(true);

        Assertions.assertThat(defaultWorkerService.createWorker(worker))
                  .as("Result is false.")
                  .isTrue();
    }

    @Test
    public void getAllWorkerTest() {
        Collection<Worker> workers = new ArrayList<>();
        workers.add(new WorkerEntity(1, "name", "position", "avatar"));
        workers.add(new WorkerEntity(2, "name", "position", "avatar"));
        workers.add(new WorkerEntity(3, "name", "position", "avatar"));
        workers.add(new WorkerEntity(4, "name", "position", "avatar"));
        Mockito.when(defaultWorkerRepository.getAllWorkers()).thenReturn(workers);

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
        Worker worker = new WorkerEntity(1,"name", "position", "avatar");
        Mockito.when(defaultWorkerRepository.getWorkerById(1)).thenReturn(Optional.of(worker));

        Assertions.assertThat(defaultWorkerService.getWorkerById(1))
                  .as("Result is false.")
                  .isNotNull();
    }

    @Test
    public void modifyWorkerTest() {
        WorkerModel worker = new WorkerModel("name", "position", "avatar");
        Mockito.when(defaultWorkerRepository.modifyWorker(1, worker)).thenReturn(true);

        Assertions.assertThat(defaultWorkerService.modifyWorker(1, worker))
                  .as("Result is false.")
                  .isTrue();
    }

    @Test
    public void deleteWorkerByIdTest() {
        Mockito.when(defaultWorkerService.deleteWorkerById(1)).thenReturn(true);

        Assertions.assertThat(defaultWorkerService.deleteWorkerById(1))
                  .as("Result is false.")
                  .isTrue();
    }
}
