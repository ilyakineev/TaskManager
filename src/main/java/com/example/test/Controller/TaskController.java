package com.example.test.Controller;

import com.example.test.Model.ModifyTaskModel;
import com.example.test.Model.SimpleTaskModel;
import com.example.test.Model.TaskModel;
import com.example.test.Service.TaskService;
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
 * Эндпоинты для запросов связанных с задачами.
 */
@RestController
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Создание задачи.
     * @param task Задача.
     * @return Результат операции.
     */
    @PostMapping(value = "/tasks")
    public ResponseEntity<?> createTask(@RequestBody TaskModel task) {
        return taskService.createTask(task)
                ? new ResponseEntity<>(HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Получение всех задач.
     * @return Коллекция задач.
     */
    @GetMapping(value = "/tasks")
    public ResponseEntity<Collection<SimpleTaskModel>> readTasks() {
        Collection<SimpleTaskModel> tasks = taskService.getAllTask();
        return !Objects.isNull(tasks) && !tasks.isEmpty()
                ? new ResponseEntity<>(tasks, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    /**
     * Получение задачи.
     * @param id Идентификатор задачи.
     * @return Задача.
     */
    @GetMapping(value = "/tasks/{id}")
    public ResponseEntity<?> readTask(
            @PathVariable(name = "id") long id) {
        TaskModel task = taskService.getTaskById(id);
        return !Objects.isNull(task)
               ? new ResponseEntity<>(task, HttpStatus.OK)
               : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Обнавление задачи.
     * @param id Идентификатор задачи.
     * @param task Модель изменения задачи.
     * @return Результат операции.
     */
    @PutMapping(value = "/tasks/{id}")
    public ResponseEntity<?> modifyTask(
            @PathVariable(name = "id") long id,
            @RequestBody ModifyTaskModel task) {
        return taskService.updateTask(id, task)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    /**
     * Удаление задачи.
     * @param id Идентификатор задачи.
     * @return Результат операции.
     */
    @DeleteMapping(value = "/tasks/{id}")
    public ResponseEntity<?> deleteTask(
            @PathVariable(name = "id") long id) {
        return taskService.deleteTaskById(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    /**
     * Назначить на задачу исполнителя.
     * @param taskId Идентификатор задачи.
     * @param workerId Идентификатор испольнителя.
     * @return Результат операции.
     */
    @PutMapping(value = "/task/{taskId}/{workerId}")
    public ResponseEntity<?> modifyTask(
            @PathVariable(name = "taskId") long taskId,
            @PathVariable(name = "workerId") long workerId) {
        return taskService.assignWorkerToTask(taskId, workerId)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}