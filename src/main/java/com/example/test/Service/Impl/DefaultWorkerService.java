package com.example.test.Service.Impl;

import com.example.test.Model.WorkerModel;
import com.example.test.Repository.WorkerRepository;
import com.example.test.Service.TaskLoader;
import com.example.test.Service.WorkerService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class DefaultWorkerService implements WorkerService {

    private final WorkerRepository workerRepository;

    private final TaskLoader taskLoader;

    @Autowired
    DefaultWorkerService(
            @Qualifier("JPAWorkerRepository") WorkerRepository workerRepository,
            TaskLoader taskLoader
    ) {
        this.workerRepository = workerRepository;
        this.taskLoader = taskLoader;
    }

    @Override
    public boolean createWorker(WorkerModel worker) {
        return workerRepository.createWorker(worker);
    }

    @Override
    public Collection<WorkerModel> getAllWorkers() {
        return workerRepository.getAllWorkers().stream()
                               .map(entity -> new WorkerModel(
                                       entity.getName(),
                                       entity.getPosition(),
                                       entity.getAvatar())
                               )
                               .toList();
    }

    @Override
    public WorkerModel getWorkerById(long id) {
        return workerRepository.getWorkerById(id)
                               .map(entity -> new WorkerModel(
                                       entity.getName(),
                                       entity.getPosition(),
                                       entity.getAvatar())
                               )
                               .orElse(null);
    }

    @Override
    public boolean modifyWorker(long id, WorkerModel worker) {
        return workerRepository.modifyWorker(id, worker);
    }

    @Override
    public boolean deleteWorkerById(long id) {
        return workerRepository.deleteWorkerById(id);
    }

    @Override
    public boolean  addTaskInPipeline(long workerId) {
        return taskLoader.addTaskInPipeline(getWorkerById(workerId));
    }

    @Override
    public boolean removeWorkerInPipeline(long workerId) {
        return taskLoader.removeWorkerInPipeline(getWorkerById(workerId));
    }

    @Override
    public Collection<WorkerModel> getStatusWorkers() {
        return taskLoader.getStatusWork();
    }
}
