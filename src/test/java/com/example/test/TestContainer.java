package com.example.test;

import com.example.test.Entity.Impl.TaskEntity;
import com.example.test.Entity.Impl.WorkerEntity;
import com.example.test.Entity.Task;
import com.example.test.Entity.Worker;
import com.example.test.Model.ModifyTaskModel;
import com.example.test.Model.SimpleTaskModel;
import com.example.test.Model.TaskModel;
import com.example.test.Model.WorkerModel;
import java.util.Collection;
import java.util.List;

public class TestContainer {
    public static final Collection<SimpleTaskModel> TASKS = List.of(
            new SimpleTaskModel(1, "title", "status"),
            new SimpleTaskModel(3, "title", "status"),
            new SimpleTaskModel(4, "title", "status"),
            new SimpleTaskModel(5, "title", "status")
    );
    public static final TaskModel TASK_MODEL = new TaskModel("title", "description", "time", "status", 1);
    ;
    public static final ModifyTaskModel MODIFY_TASK_MODEL = new ModifyTaskModel("title", "description", "time", "status");
    public static final WorkerModel WORKER_MODEL = new WorkerModel("name", "position", "avatar");
    public static final List<WorkerModel> WORKERS = List.of(
            new WorkerModel("Ivan", "Worker", "avatar"),
            new WorkerModel("Max", "Manager", "avatar"),
            new WorkerModel("Tom", "Taxi driver", "avatar")
    );
    public static final Worker WORKER_ENTITY = new WorkerEntity(1, "name", "position", "avatar");
    public static final Collection<Worker> WORKERS_ENTITY = List.of(
            new WorkerEntity(1, "Ivan", "Worker", "avatar"),
            new WorkerEntity(2, "Max", "Manager", "avatar"),
            new WorkerEntity(3, "Tom", "Taxi driver", "avatar"),
            new WorkerEntity(4, "name", "position", "avatar"),
            new WorkerEntity(5, "name", "position", "avatar")
    );
    public static final Collection<Task> TASKS_ENTITY = List.of(
            new TaskEntity(1, "title", "description", "time", "status", 1),
            new TaskEntity(2, "title", "description", "time", "status", 2),
            new TaskEntity(3, "title", "description", "time", "status", 1),
            new TaskEntity(4, "title", "description", "time", "status", 3)
    );
    public static final Collection<TaskModel> TASKS_MODEL = List.of(
            new TaskModel("title", "description", "time", "status", 1),
            new TaskModel("title", "description", "time", "status", 1),
            new TaskModel("title", "description", "time", "status", 1)
    );
}
