package com.example.test.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Модель работника.
 */
public class WorkerModel {
    @JsonProperty("name")
    private String name;
    @JsonProperty("position")
    private String position;
    @JsonProperty("avatar")
    private String avatar;

    public WorkerModel() {}

    public WorkerModel(
            String name,
            String position,
            String avatar
    ) {
        this.name = name;
        this.position = position;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    void setPosition(String position) {
        this.position = position;
    }

    public String getAvatar() {
        return avatar;
    }

    void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}