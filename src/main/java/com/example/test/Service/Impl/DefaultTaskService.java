package com.example.test.Service.Impl;

import com.example.test.Model.ModifyTaskModel;
import com.example.test.Model.SimpleTaskModel;
import com.example.test.Model.TaskModel;
import com.example.test.Repository.TaskRepository;
import com.example.test.Service.TaskManager;
import com.example.test.Service.TaskService;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultTaskService implements TaskService {

    private final TaskRepository taskRepository;

    private final TaskManager taskManager;

    @Autowired
    public DefaultTaskService(
            TaskRepository taskRepository,
            TaskManager taskManager)
    {
        this.taskRepository = taskRepository;
        this.taskManager = taskManager;
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

    @Override
    public boolean addTaskInPipeline(long id) {
        return taskManager.addTaskInPipeline(getTaskById(id));
    }

    @Override
    public boolean addTaskInPipeline(List<Long> tasks) {
        return !tasks.stream().map(task -> taskManager.addTaskInPipeline(getTaskById(task)))
                     .collect(Collectors.toList()).isEmpty();
    }

    @Override
    public Collection<TaskModel> getStatusTask() {
        return taskManager.getStatusTask();
    }
}
