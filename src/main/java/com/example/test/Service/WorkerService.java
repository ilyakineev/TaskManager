package com.example.test.Service;

import com.example.test.Model.WorkerModel;
import java.util.Collection;

/**
 * Интерфейс команд при работе с работниками.
 */
public interface WorkerService {

    /**
     * Создание работника.
     * @param worker Новый работник.
     * @return Результат операции.
     */
    boolean createWorker(WorkerModel worker);

    /**
     * Получение всех работников.
     * @return Коллекция работников.
     */
    Collection<WorkerModel> getAllWorkers();

    /**
     * Получение работника по идентификатору.
     * @param id Идентификатор работника.
     * @return Работник.
     */
    WorkerModel getWorkerById(long id);

    /**
     * Изменение данных работника.
     * @param id Идентификатор работника.
     * @param worker Работник.
     * @return Результат операции.
     */
    boolean modifyWorker(long id, WorkerModel worker);

    /**
     * Удаление работника.
     * @param id Идентификатор работника.
     * @return результат операции.
     */
    boolean deleteWorkerById(long id);

    /**
     * Активировать рабочего.
     * @param workerId Идентификатор задачи.
     * @return Результат операции.
     */
    boolean addTaskInPipeline(long workerId);

    /**
     * Деактивировать рабочего.
     * @param workerId Идентификатор рабочего.
     * @return Результат операции.
     */
    boolean removeWorkerInPipeline(long workerId);

    /**
     * Получить статус активированных задач.
     * @return Результат операции.
     */
    Collection<WorkerModel> getStatusWorkers();
}
