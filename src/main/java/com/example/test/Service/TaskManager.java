package com.example.test.Service;

import com.example.test.Model.TaskModel;
import java.util.Collection;
import java.util.LinkedList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

@Service
@Scope(WebApplicationContext.SCOPE_APPLICATION)
public class TaskManager {
    private final Logger LOGGER = LoggerFactory.getLogger(TaskManager.class);
    private final LinkedList<TaskModel> taskPipeline;

    @Value("${taskManager.maxTasksInPipeline}")
    private int maxTasksInPipeline;

    @Value("${taskManager.minTasksInPipeline}")
    private int minTasksInPipeline;
    private int taskCounter = 0;

    public TaskManager() {
        this.taskPipeline = new LinkedList<>();
    }

    public synchronized boolean addTaskInPipeline(TaskModel task) {
        try {
            if (taskCounter < maxTasksInPipeline) {
                notifyAll();
                taskPipeline.add(task);
                taskCounter++;
                LOGGER.info("Задач в очереди: {}", taskCounter);
            } else {
                wait();
                return false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    public synchronized TaskModel getTaskInPipeline() {
        try {
            if (taskCounter > minTasksInPipeline) {
                notifyAll();
                LOGGER.info("Задач в очереди: {}", taskCounter);
                TaskModel first = taskPipeline.getFirst();
                taskPipeline.removeFirst();
                taskCounter--;
                return first;
            }
            LOGGER.info("Задач закончились.");
            wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Collection<TaskModel> getStatusTask() {
        return taskPipeline.stream().toList();
    }
}
