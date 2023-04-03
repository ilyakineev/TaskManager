package com.example.test.Service;

import com.example.test.Model.TaskModel;
import java.time.LocalDateTime;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

@Service
@Scope(WebApplicationContext.SCOPE_APPLICATION)
public class TaskGenerator implements  Runnable{

    private final TaskManager taskManager;
    private final int taskCount;

    public TaskGenerator(TaskManager taskManager) {
        this.taskCount = 1000;
        this.taskManager = taskManager;
    }

    @Override
    public void run() {
        int count = 0;
        while (count < taskCount) {
            count++;
            TaskModel newTask = getNewTask();
            System.out.println("Новая задача!" + newTask);

            taskManager.addTaskInPipeline(newTask);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static TaskModel getNewTask() {
        return new TaskModel("Задача.", "Описание задачи.", LocalDateTime.now().toString(), TaskStatus.NEW_TASK.name(), 0L);
    }
}
