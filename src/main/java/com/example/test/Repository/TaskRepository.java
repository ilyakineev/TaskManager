package com.example.test.Repository;

import com.example.test.Entity.Task;
import com.example.test.Model.ModifyTaskModel;
import com.example.test.Model.TaskModel;
import java.util.Collection;
import java.util.Optional;

/**
 * Репозиторий задач.
 */
public interface TaskRepository {

    /**
     * Создание задачи.
     * @param task Модель задачи.
     * @return Результат операции.
     */
    boolean createTask(TaskModel task);

    /**
     * Получение всех задач.
     * @return Коллекция задач.
     */
    Collection<Task> getAllTasks();

    /**
     * Получение задачи по идентификатору.
     * @param id Идентификатор задачи.
     * @return Задача.
     */
    Optional<Task> getTaskById(long id);

    /**
     * Изменение данных задачи.
     * @param id Идентификатор задачи.
     * @param task Задача.
     * @return Результат операции.
     */
    boolean modifyTask(long id, ModifyTaskModel task);

    /**
     * Удаление задачи.
     * @param id Идентификатор задачи.
     * @return Результат операции.
     */
    boolean deleteTaskById(long id);

    /**
     * Назначить на задачу исполнителя.
     * @param taskId Идентификатор задачи.
     * @param workerId Идентификатор исполнителя.
     * @return Результат операции.
     */
    boolean assignWorkerToTask(long taskId, long workerId);
}
