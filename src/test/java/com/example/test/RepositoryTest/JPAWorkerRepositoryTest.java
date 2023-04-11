package com.example.test.RepositoryTest;

import com.example.test.Entity.Worker;
import com.example.test.Repository.Impl.JPAWorkerRepository;
import com.example.test.TestContainer;
import java.util.Collection;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class JPAWorkerRepositoryTest {
    @Autowired
    private JPAWorkerRepository defaultWorkerRepository;

    @Test
    public void createWorkerTest() {
        Assertions.assertThat(defaultWorkerRepository.createWorker(TestContainer.WORKER_MODEL))
                  .as("Result is false.")
                  .isTrue();
    }

    @Test
    public void getAllWorkerTest() {
        final Collection<Worker> result = defaultWorkerRepository.getAllWorkers();
        Assertions.assertThat(result)
                  .as("Result is null.")
                  .isNotNull();
        Assertions.assertThat(result)
                  .as("Result is empty.")
                  .isNotEmpty();
    }

    @Test
    public void getWorkerByIdTest() {
        Worker result = defaultWorkerRepository.getWorkerById(1).orElse(null);
        Assertions.assertThat(result)
                  .as("Result is null.")
                  .isNotNull();
        Assertions.assertThat(result.getId())
                  .as("Result is incorrect.")
                  .isEqualTo(TestContainer.WORKER_ENTITY.getId());
        Assertions.assertThat(result.getName())
                  .as("Result is incorrect.")
                  .isEqualTo(TestContainer.WORKER_ENTITY.getName());
        Assertions.assertThat(result.getAvatar())
                  .as("Result is incorrect.")
                  .isEqualTo(TestContainer.WORKER_ENTITY.getAvatar());
        Assertions.assertThat(result.getPosition())
                  .as("Result is incorrect.")
                  .isEqualTo(TestContainer.WORKER_ENTITY.getPosition());
    }

    @Test
    public void modifyWorkerTest() {
        Assertions.assertThat(defaultWorkerRepository.modifyWorker(1, TestContainer.WORKER_MODEL))
                  .as("Result is false.")
                  .isTrue();
    }

    @Test
    public void deleteWorkerByIdTest() {
        Assertions.assertThat(defaultWorkerRepository.deleteWorkerById(2))
                  .as("Result is false.")
                  .isTrue();
    }
}
