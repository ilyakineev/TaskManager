package com.example.test.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Модель работника.
 */
public class WorkerModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final WorkerModel that = (WorkerModel) o;
        return Objects.equals(name, that.name) && Objects.equals(position, that.position) && Objects.equals(avatar, that.avatar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, position, avatar);
    }

    @Override
    public String toString() {
        return "WorkerModel{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}