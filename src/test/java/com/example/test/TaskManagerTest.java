package com.example.test;

import com.example.test.Service.TaskManager;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskManagerTest {

    @Autowired
    private static TaskManager taskManager;


    @BeforeAll
    public static void initTest() {

    }


    @AfterAll
    public static void test() {
    }

    @Test
    public void createWorkerTest() {

    }
}
