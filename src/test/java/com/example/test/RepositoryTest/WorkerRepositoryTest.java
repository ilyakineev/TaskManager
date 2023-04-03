package com.example.test.RepositoryTest;

import com.example.test.Entity.Impl.WorkerEntity;
import com.example.test.Entity.Worker;
import com.example.test.Model.WorkerModel;
import com.example.test.Repository.Impl.DefaultWorkerRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class WorkerRepositoryTest {
    @MockBean
    private DefaultWorkerRepository defaultWorkerRepository;

    @Test
    public void createWorkerTest() {
        WorkerModel worker = new WorkerModel("name", "position", "avatar");
        Mockito.when(defaultWorkerRepository.createWorker(worker))
               .thenReturn(true);
    }

    @Test
    public void getAllWorkerTest() {
        Collection<Worker> workers = new ArrayList<>();
        workers.add(new WorkerEntity(1, "name", "position", "avatar"));
        workers.add(new WorkerEntity(2, "name", "position", "avatar"));
        workers.add(new WorkerEntity(3, "name", "position", "avatar"));
        workers.add(new WorkerEntity(4, "name", "position", "avatar"));
        workers.add(new WorkerEntity(5, "name", "position", "avatar"));
        Mockito.when(defaultWorkerRepository.getAllWorkers())
               .thenReturn(workers);
    }

    @Test
    public void getWorkerByIdTest() {
        Worker worker = new WorkerEntity(1, "name", "position", "avatar");
        Mockito.when(defaultWorkerRepository.getWorkerById(1))
               .thenReturn(Optional.of(worker));
    }

    @Test
    public void modifyWorkerTest() {
        WorkerModel worker = new WorkerModel("name", "position", "avatar");
        Mockito.when(defaultWorkerRepository.modifyWorker(1, worker))
               .thenReturn(true);
    }

    @Test
    public void deleteWorkerByIdTest() {
        Mockito.when(defaultWorkerRepository.deleteWorkerById(2))
               .thenReturn(true);
    }
}
