package com.example.test.Service;

import java.util.Optional;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

@Service
@Scope(WebApplicationContext.SCOPE_APPLICATION)
public class TaskLoader implements Runnable {

    private TaskManager taskManager;

    public TaskLoader(TaskManager taskManager) {this.taskManager = taskManager;}

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(300);
                Optional.of(taskManager.getTaskInPipeline())
                        .ifPresent(System.out::println);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
