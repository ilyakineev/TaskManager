package com.example.test.Service;

import com.example.test.Model.TaskModel;
import java.util.LinkedList;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

@Service
@Scope(WebApplicationContext.SCOPE_APPLICATION)
public class TaskManager {
    private final LinkedList<TaskModel> taskPipeline;
    private static final int maxTasksInPipeline = 100;
    private static final int minTasksInPipeline = 0;
    private int taskCounter = 0;

    public TaskManager() {
        this.taskPipeline = new LinkedList<>();
    }

    public synchronized boolean addTaskInPipeline(TaskModel task) {
        try {
            if (taskCounter < maxTasksInPipeline) {
                notifyAll();
                taskPipeline.addLast(task);
                System.out.println("Задач в очереди: " + taskCounter);
                taskCounter++;
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
                System.out.println("Задач в очереди: " + taskCounter);
                taskCounter--;
                notifyAll();
                return taskPipeline.getFirst();
            }
            System.out.println("Задач закончились");
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
