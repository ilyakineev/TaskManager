package com.example.test.Entity.Impl;

import com.example.test.Entity.Task;

/**
 * Задача.
 */
public class TaskEntity implements Task {
    private long id;
    private String title;
    private String description;
    private String time;
    private String status;
    private long performer;

    public TaskEntity() {}

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
