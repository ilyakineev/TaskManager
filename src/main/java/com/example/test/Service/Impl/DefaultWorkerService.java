package com.example.test.Service.Impl;

import com.example.test.Model.WorkerModel;
import com.example.test.Repository.WorkerRepository;
import com.example.test.Service.WorkerService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultWorkerService implements WorkerService {

    private final WorkerRepository workerRepository;


    @Autowired
    DefaultWorkerService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
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
}
