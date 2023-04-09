package com.example.test.Model;

import com.example.test.Service.TaskManager;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkSpace extends Thread {

    private final Logger LOGGER = LoggerFactory.getLogger(WorkSpace.class);

    private final TaskManager taskManager;

    private volatile boolean mFinish = false;

    @JsonProperty("WorkerModel")
    private WorkerModel workerModel;

    public WorkSpace(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public void run() {
        while (true) {
            if (!mFinish) {
                try {
                    if (Objects.nonNull(workerModel)) {
                        Thread.sleep(300);
                        TaskModel taskInPipeline = taskManager.getTaskInPipeline();
                        LOGGER.info("Рабочий {} выполнили задачу: {}", workerModel, taskInPipeline);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                return;
            }
        }
    }

    public void setWorkerModel(WorkerModel workerModel) {
        this.workerModel = workerModel;
    }

    public WorkerModel getWorkerModel() {
        return workerModel;
    }

    @Override
    public String toString() {
        return "WorkSpace{" +
                "workerModel=" + workerModel +
                '}';
    }

    public void finish() {
        this.mFinish = true;
    }
}
