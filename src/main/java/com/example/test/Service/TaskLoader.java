package com.example.test.Service;

import com.example.test.Model.WorkSpace;
import com.example.test.Model.WorkerModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskLoader {
    private final Logger LOGGER = LoggerFactory.getLogger(TaskManager.class);

    private final Collection<WorkSpace> workerPipeline;
    private static final int maxWorkersInWorkSpace = 10;
    private static final int minWorkersInWorkSpace = 0;
    private int workCounter = 0;
    private final TaskManager taskManager;

    @Autowired
    public TaskLoader(TaskManager taskManager) {
        this.taskManager = taskManager;
        workerPipeline = new ArrayList<>();
    }

    public synchronized boolean addTaskInPipeline(WorkerModel worker) {
        if (workCounter < maxWorkersInWorkSpace) {
            WorkSpace workSpace = new WorkSpace(taskManager);
            workSpace.setWorkerModel(worker);
            workSpace.start();
            workerPipeline.add(workSpace);
            workCounter++;
            LOGGER.info("Рабочий {} занял рабочее место.", workSpace);
            return true;
        } else {
            LOGGER.info("У рабочего {} нет рабочего места.", worker);
            return false;
        }
    }

    public synchronized boolean removeWorkerInPipeline(WorkerModel worker) {
        if (workCounter > minWorkersInWorkSpace) {
            final WorkSpace workSpace = workerPipeline.stream()
                                                      .filter(space -> space.getWorkerModel()
                                                                            .equals(worker))
                                                      .findFirst()
                                                      .orElseGet(null);
            workSpace.finish();
            workerPipeline.remove(workSpace);
            workCounter--;
            LOGGER.info("Рабочий {} покинул рабочее место", worker);
            return true;
        } else {
            LOGGER.info("Рабочего {} не было на рабочем месте", worker);
            return false;
        }
    }

    public Collection<WorkerModel> getStatusWork() {
        return workerPipeline.stream()
                             .map(WorkSpace::getWorkerModel)
                             .collect(Collectors.toList());
    }
}
