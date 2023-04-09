package com.example.test.Repository.Impl;

import com.example.test.Entity.Impl.TaskEntity;
import com.example.test.Entity.Task;
import com.example.test.Model.ModifyTaskModel;
import com.example.test.Model.TaskModel;
import com.example.test.Repository.TaskRepository;
import java.util.Collection;
import java.util.Optional;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий задач.
 */
@Repository
public class DefaultTaskRepository implements TaskRepository {

    private final JdbcTemplate jdbcTemplate;

    public DefaultTaskRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean createTask(TaskModel task) {
        int result = jdbcTemplate.update("INSERT INTO task (title, description, time, status, performer) VALUES(?,?,?,?,?)",
                task.getTitle(), task.getDescription(), task.getTime(), task.getStatus(), task.getPerformer());
        return isModifyEntity(result);
    }

    @Override
    public Collection<Task> getAllTasks() {
        return jdbcTemplate.query("SELECT id, title, description, time, status, performer FROM task",
                                   BeanPropertyRowMapper.newInstance(TaskEntity.class))
                           .stream()
                           .map(e -> (Task) e)
                           .toList();
    }

    @Override
    public Optional<Task> getTaskById(long id) {
        Task task = jdbcTemplate.queryForObject("SELECT id, title, description, time, status, performer FROM task WHERE id = ?",
                BeanPropertyRowMapper.newInstance(TaskEntity.class), id);
        return Optional.of(task);
    }

    @Override
    public boolean modifyTask(long id, ModifyTaskModel task) {
        int result = jdbcTemplate.update("UPDATE task SET title = ?, description = ?, time = ?, status = ? WHERE id = ?",
                task.getTitle(), task.getDescription(), task.getTime(), task.getStatus(), id);
        return isModifyEntity(result);
    }

    @Override
    public boolean deleteTaskById(long id) {
        int result = jdbcTemplate.update("DELETE FROM task WHERE id = ?", id);
        return isModifyEntity(result);
    }

    @Override
    public boolean assignWorkerToTask(long taskId, long workerId) {
        int result = jdbcTemplate.update("UPDATE task SET performer=? WHERE id = ?",
                workerId, taskId);
        return isModifyEntity(result);
    }

    /**
     * Были ли изменения в таблице.
     * @param result Количество измененных строк в таблице.
     * @return true если изменения произошли.
     */
    private static boolean isModifyEntity(int result) {
        return result != 0;
    }
}
