package com.example.test.Controller;

import com.example.test.Model.WorkerModel;
import com.example.test.Service.WorkerService;
import java.util.Collection;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Эндпоинты для запросов связанных с работниками.
 */
@RestController
public class WorkerController {

    private final WorkerService workerService;

    @Autowired
    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    /**
     * Создание работника.
     * @param worker Модель работника.
     * @return Результат операции.
     */
    @PostMapping(value = "/workers")
    public ResponseEntity<?> createWorker(@RequestBody WorkerModel worker) {
        return workerService.createWorker(worker)
                ? new ResponseEntity<>(HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Получение всех работников.
     * @return Коллекция работников.
     */
    @GetMapping(value = "/workers")
    public ResponseEntity<Collection<WorkerModel>> readWorkers() {
        final Collection<WorkerModel> workers = workerService.getAllWorkers();
        return !Objects.isNull(workers) && !workers.isEmpty()
                ? new ResponseEntity<>(workers, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Получение работника по идентификатору.
     * @param id Идентификатор работника.
     * @return Модель работника.
     */
    @GetMapping(value = "/workers/{id}")
    public ResponseEntity<?> readWorker(
            @PathVariable(name = "id") long id) {
        WorkerModel worker = workerService.getWorkerById(id);
        return !Objects.isNull(worker)
                ? new ResponseEntity<>(worker, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Изменение данных работника.
     * @param id Идентификатор работника.
     * @param worker Модель работника.
     * @return Результат операции.
     */
    @PutMapping(value = "/workers/{id}")
    public ResponseEntity<?> updateWorker(
            @PathVariable(name = "id") long id,
            @RequestBody WorkerModel worker) {
        return workerService.modifyWorker(id, worker)
                ? new ResponseEntity<>(id, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    /**
     * Удаление работника.
     * @param id Идентификатор работника.
     * @return Результат операции.
     */
    @DeleteMapping(value = "/workers/{id}")
    public ResponseEntity<?> deleteWorker(
            @PathVariable(name = "id") long id) {
        return workerService.deleteWorkerById(id)
                ? new ResponseEntity<>(id, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    /**
     * Активировать рабочего.
     * @param workerId Идентификатор задачи.
     * @return Результат операции.
     */
    @PutMapping(value = "/worker/{workerId}")
    public ResponseEntity<?> addTaskInPipeline(
            @PathVariable(name = "workerId") long workerId) {
        return workerService.addTaskInPipeline(workerId)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    /**
     * Деактивировать рабочего.
     * @param workerId Идентификатор рабочего.
     * @return Результат операции.
     */
    @DeleteMapping(value = "/worker/{workerId}")
    public ResponseEntity<?> removeWorkerInPipeline(
            @PathVariable(name = "workerId") long workerId) {
        return workerService.removeWorkerInPipeline(workerId)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    /**
     * Получить статус активированных задач.
     * @return Результат операции.
     */
    @GetMapping(value = "/workers/status")
    public ResponseEntity<Collection<WorkerModel>> getStatusWorkers() {
        Collection<WorkerModel> workers = workerService.getStatusWorkers();
        return !Objects.isNull(workers) && !workers.isEmpty()
                ? new ResponseEntity<>(workers, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}