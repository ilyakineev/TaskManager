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
     * @return Коллекция работникив.
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
     * @return результаит операции.
     */
    boolean deleteWorkerById(long id);
}
