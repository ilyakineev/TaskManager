package com.example.test.Repository.Impl;

import com.example.test.Entity.Impl.WorkerEntity;
import com.example.test.Entity.Worker;
import com.example.test.Model.WorkerModel;
import com.example.test.Repository.WorkerRepository;
import java.util.Collection;
import java.util.Optional;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JDBCWorkerRepository implements WorkerRepository {

    private final JdbcTemplate jdbcTemplate;

    public JDBCWorkerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean createWorker(WorkerModel worker) {
        int result = jdbcTemplate.update("INSERT INTO worker (name, position, avatar) VALUES(?,?,?)",
                worker.getName(), worker.getPosition(), worker.getAvatar());
        return isModifyEntity(result);
    }

    @Override
    public Collection<Worker> getAllWorkers() {
        return jdbcTemplate.query("SELECT id, name, position, avatar from worker",
                                   BeanPropertyRowMapper.newInstance(WorkerEntity.class))
                           .stream()
                           .map(e -> (Worker) e)
                           .toList();
    }

    @Override
    public Optional<Worker> getWorkerById(long id) {
        Worker worker = jdbcTemplate.queryForObject("SELECT id, name, position, avatar FROM worker WHERE id = ?",
                BeanPropertyRowMapper.newInstance(WorkerEntity.class), id);
        return Optional.of(worker);
    }

    @Override
    public boolean modifyWorker(long id, WorkerModel worker) {
        int result = jdbcTemplate.update("UPDATE worker SET name = ?, position = ?, avatar = ? WHERE id = ?",
                worker.getName(), worker.getPosition(), worker.getAvatar(), id);
        return isModifyEntity(result);
    }

    @Override
    public boolean deleteWorkerById(long id) {
        int result = jdbcTemplate.update("DELETE FROM worker WHERE id = ?", id);
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
