package com.example.test.Repository;

import com.example.test.Entity.Worker;
import com.example.test.Model.WorkerModel;
import java.util.Collection;
import java.util.Optional;

/**
 * Репозиторий работников.
 */
public interface WorkerRepository {

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
    Collection<Worker> getAllWorkers();

    /**
     * Получение работника по идентификатору.
     * @param id Идентификатор работника.
     * @return Работник.
     */
    Optional<Worker> getWorkerById(long id);

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
