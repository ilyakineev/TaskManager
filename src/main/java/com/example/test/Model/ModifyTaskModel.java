package com.example.test.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serial;
import java.io.Serializable;

/**
 * Модель изменения задачи.
 */
public class ModifyTaskModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("time")
    private String time;
    @JsonProperty("status")
    private String status;

    public ModifyTaskModel() {}

    public ModifyTaskModel(
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

    public String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    void setStatus(String status) {
        this.status = status;
    }
}