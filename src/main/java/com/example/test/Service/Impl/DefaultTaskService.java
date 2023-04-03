package com.example.test.Service.Impl;

import com.example.test.Model.ModifyTaskModel;
import com.example.test.Model.SimpleTaskModel;
import com.example.test.Model.TaskModel;
import com.example.test.Repository.TaskRepository;
import com.example.test.Service.TaskService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultTaskService implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public DefaultTaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public boolean createTask(TaskModel task) {
        return true;
    }

    @Override
    public Collection<SimpleTaskModel> getAllTask() {
        return taskRepository.getAllTasks().stream()
                              .map(entity -> new SimpleTaskModel(
                                      entity.getId(),
                                      entity.getTitle(),
                                      entity.getStatus())
                              ).toList();
    }

    @Override
    public TaskModel getTaskById(long id) {
        return taskRepository.getTaskById(id)
                             .map(entity -> new TaskModel(
                                     entity.getTitle(),
                                     entity.getDescription(),
                                     entity.getTime(),
                                     entity.getStatus(),
                                     entity.getPerformer())
                             )
                             .orElse(null);
    }

    @Override
    public boolean updateTask(long id, ModifyTaskModel task) {
        return taskRepository.modifyTask(id, task);
    }

    @Override
    public boolean deleteTaskById(long id) {
        return taskRepository.deleteTaskById(id);
    }

    @Override
    public boolean assignWorkerToTask(long taskId, long workerId) {
        return taskRepository.assignWorkerToTask(taskId, workerId);
    }
}
