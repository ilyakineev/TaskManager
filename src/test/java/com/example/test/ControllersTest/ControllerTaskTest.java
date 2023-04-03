package com.example.test.ControllersTest;

import com.example.test.Model.ModifyTaskModel;
import com.example.test.Model.SimpleTaskModel;
import com.example.test.Model.TaskModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Collection;
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
public class ControllerTaskTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createTaskTest() throws Exception {
        TaskModel task = new TaskModel("title", "description", "time", "status", 2);
        mockMvc.perform(post("/tasks").content(objectMapper.writeValueAsString(task))
                                      .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isCreated());
    }

    @Test
    public void readTasksTest() throws Exception {
        Collection<SimpleTaskModel> tasks = new ArrayList<>();
        tasks.add(new SimpleTaskModel(1, "title", "status"));
        tasks.add(new SimpleTaskModel(2, "title", "status"));
        tasks.add(new SimpleTaskModel(3, "title", "status"));
        tasks.add(new SimpleTaskModel(4, "title", "status"));
        tasks.add(new SimpleTaskModel(5, "title", "status"));
        mockMvc.perform(get("/tasks"))
               .andExpect(status().isOk())
               .andExpect(content().json(objectMapper.writeValueAsString(tasks)));
    }

    @Test
    public void readTaskByIdTest() throws Exception {
        TaskModel task = new TaskModel("title", "description", "time", "status", 2);
        mockMvc.perform(get("/tasks/{id}", 2))
               .andExpect(status().isOk())
               .andExpect(content().json(objectMapper.writeValueAsString(task)));
    }

    @Test
    public void updateTaskByIdTest() throws Exception {
        ModifyTaskModel model = new ModifyTaskModel("title", "description", "time", "status");
        mockMvc.perform(put("/tasks/{id}", 2).content(objectMapper.writeValueAsString(model))
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
        mockMvc.perform(put("/task/{taskId}/{workerId}", 2, 3))
               .andExpect(status().isOk());
    }
}
