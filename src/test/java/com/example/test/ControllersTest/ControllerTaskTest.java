package com.example.test.ControllersTest;

import com.example.test.TestContainer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTaskTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createTaskTest() throws Exception {
        mockMvc.perform(post("/tasks").content(objectMapper.writeValueAsString(TestContainer.TASK_MODEL))
                                      .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isCreated());
    }

    @Test
    public void readTasksTest() throws Exception {
        mockMvc.perform(get("/tasks"))
               .andExpect(status().isOk())
               .andExpect(content().json(objectMapper.writeValueAsString(TestContainer.TASKS)));
    }

    @Test
    public void readTaskByIdTest() throws Exception {
        mockMvc.perform(get("/tasks/{id}", 1))
               .andExpect(status().isOk())
               .andExpect(content().json(objectMapper.writeValueAsString(TestContainer.TASK_MODEL)));
    }

    @Test
    public void updateTaskByIdTest() throws Exception {
        mockMvc.perform(put("/tasks/{id}", 3)
                       .content(objectMapper.writeValueAsString(TestContainer.MODIFY_TASK_MODEL))
                       .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk());
    }

    @Test
    public void deleteTaskByIdTest() throws Exception {
        mockMvc.perform(delete("/tasks/{id}", 1))
               .andExpect(status().isOk());
    }

    @Test
    public void assignWorkerToTask() throws Exception {
        mockMvc.perform(patch("/tasks/{taskId}/{workerId}", 3, 1))
               .andExpect(status().isOk());
    }
}
