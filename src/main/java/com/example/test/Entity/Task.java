package com.example.test.Entity;

/**
 * Задача.
 */
public interface Task {

    /**
     * @return Идентификатор задачи.
     */
    long getId();

    /**
     * @return Заголовок задачи.
     */
    String getTitle();

    /**
     * @return Описание задачи.
     */
    String getDescription();

    /**
     * @return Время о создании задачи.
     */
    String getTime();

    /**
     * @return Статус задачи.
     */
    String getStatus();

    /**
     * @return Исполнитель задачи.
     */
    long getPerformer();
}