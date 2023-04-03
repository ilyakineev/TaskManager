package com.example.test.Service;

import com.example.test.Model.ModifyTaskModel;
import com.example.test.Model.SimpleTaskModel;
import com.example.test.Model.TaskModel;
import java.util.Collection;

/**
 * Интерфейс команд при работе с задачами.
 */
public interface TaskService {

    /**
     * Создание задачи.
     * @param task Задача.
     * @return Результат операции.
     */
    boolean createTask(TaskModel task);

    /**
     * Получение всех задач.
     * @return Коллекция задач.
     */
    Collection<SimpleTaskModel> getAllTask();

    /**
     * Получение задачи.
     * @param id Идентификатор задачи.
     * @return Задача.
     */
    TaskModel getTaskById(long id);

    /**
     * Обнавление задачи.
     * @param id Идентификатор задачи.
     * @param task Задача.
     * @return Результат операции.
     */
    boolean updateTask(long id, ModifyTaskModel task);

    /**
     * Удаление задачи.
     * @param id Идентификатор задачи.
     * @return Результат операции.
     */
    boolean deleteTaskById(long id);

    /**
     * Назначить на задачу исполнителя.
     * @param taskId Идентификатор задачи.
     * @param workerId Идентификатор испольнителя.
     * @return Результат операции.
     */
    boolean assignWorkerToTask(long taskId, long workerId);
}
