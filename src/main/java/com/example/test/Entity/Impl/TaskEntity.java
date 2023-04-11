package com.example.test.Entity.Impl;

import com.example.test.Entity.Task;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 * Задача.
 */
@NamedQuery(name = TaskEntity.GET_ALL_TASKS,
        query = "SELECT task FROM TaskEntity task")
@NamedQuery(name = TaskEntity.ASSIGN_PERFORMER,
        query = "UPDATE TaskEntity task " +
                "SET task.performer = :workerId " +
                "WHERE task.id = :taskId")
@Entity
@Table(name = "task")
public class TaskEntity implements Task {
    public static final String GET_ALL_TASKS = "TaskEntity.getAllTasks";
    public static final String ASSIGN_PERFORMER = "TaskEntity.assignPerformer";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    private String time;
    private String status;
    private long performer;

    public TaskEntity() {}

    public TaskEntity(
            String title,
            String description,
            String time,
            String status
    ) {
        this.title = title;
        this.description = description;
        this.time = time;
        this.status = status;
    }

    public TaskEntity(
            long id,
            String title,
            String description,
            String time,
            String status,
            long performer
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.time = time;
        this.status = status;
        this.performer = performer;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public long getPerformer() {
        return performer;
    }

    public void setPerformer(long performer) {
        this.performer = performer;
    }
}
