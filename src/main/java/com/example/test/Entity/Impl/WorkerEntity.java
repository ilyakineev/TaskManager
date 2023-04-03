package com.example.test.Entity.Impl;

import com.example.test.Entity.Worker;

/**
 * Работник.
 */
public class WorkerEntity implements Worker {
    private long id;
    private String name;
    private String position;
    private String avatar;
    public WorkerEntity() {}

    public WorkerEntity(
            long id,
            String name,
            String position,
            String avatar
    ) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.avatar = avatar;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
