package com.example.test.ControllersTest;

import com.example.test.Model.WorkerModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerWorkerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createWorkerTest() throws Exception {
        WorkerModel worker = new WorkerModel("name", "position", "avatar");
        mockMvc.perform(post("/workers")
                       .content(objectMapper.writeValueAsString(worker))
                       .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isCreated());
    }

    @Test
    public void readWorkerTest() throws Exception {
        final List<WorkerModel> workers = new ArrayList<>();
        workers.add(new WorkerModel("name", "position", "avatar"));
        workers.add(new WorkerModel("name", "position", "avatar"));
        workers.add(new WorkerModel("name", "position", "avatar"));
        mockMvc.perform(get("/workers"))
               .andExpect(status().isOk())
               .andExpect(content().json(objectMapper.writeValueAsString(workers)));
    }

    @Test
    public void readWorkerByIdTest() throws Exception {
        WorkerModel worker = new WorkerModel("name", "position", "avatar");
        mockMvc.perform(get("/workers/{id}", 2))
               .andExpect(status().isOk())
               .andExpect(content().json(objectMapper.writeValueAsString(worker)));

    }

    @Test
    public void updateWorkerByIdTest() throws Exception {
        WorkerModel worker = new WorkerModel("name", "position", "avatar");
        mockMvc.perform(put("/workers/{id}", 2)
                       .content(objectMapper.writeValueAsString(worker))
                       .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk());
    }

    @Test
    public void deleteWorkerByIdTest() throws Exception {
        mockMvc.perform(delete("/workers/{id}",4))
               .andExpect(status().isOk());
    }
}
