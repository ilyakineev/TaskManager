package com.example.test.Service;

import com.example.test.Model.ModifyTaskModel;
import com.example.test.Model.SimpleTaskModel;
import com.example.test.Model.TaskModel;
import java.util.Collection;
import java.util.List;

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
     * Обновление задачи.
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
     * @param workerId Идентификатор исполнителя.
     * @return Результат операции.
     */
    boolean assignWorkerToTask(long taskId, long workerId);

    /**
     * Поставить задачу в очередь.
     * @param id Идентификатор задачи.
     * @return Результат операции.
     */
    boolean addTaskInPipeline(long id);

    /**
     * Поставить задачи в очередь.
     * @param tasks Список идентификатор задач.
     * @return Результат операции.
     */
    boolean addTaskInPipeline(List<Long> tasks);

    /**
     * Получить статус запущенных задач.
     * @return Результат операции.
     */
    Collection<TaskModel> getStatusTask();
}
