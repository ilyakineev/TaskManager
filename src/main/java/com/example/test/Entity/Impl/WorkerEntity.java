package com.example.test.Entity.Impl;

import com.example.test.Entity.Worker;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 * Работник.
 */
@NamedQuery(name = WorkerEntity.GET_ALL_WORKERS,
        query = "SELECT worker FROM WorkerEntity worker")
@Entity
@Table(name = "worker")
public class WorkerEntity implements Worker {
    public static final String GET_ALL_WORKERS = "WorkerEntity.getAllWorkers";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String position;
    private String avatar;

    public WorkerEntity() {}

    public WorkerEntity(
            String name,
            String position,
            String avatar
    ) {
        this.name = name;
        this.position = position;
        this.avatar = avatar;
    }

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
