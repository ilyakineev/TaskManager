package com.example.test.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serial;
import java.io.Serializable;

/**
 * Простая модель задачи.
 */
public class SimpleTaskModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @JsonProperty("id")
    private long id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("status")
    private String status;

    public SimpleTaskModel() {}

    public SimpleTaskModel(long id, String title, String status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }

    long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}