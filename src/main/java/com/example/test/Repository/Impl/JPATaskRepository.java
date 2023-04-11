package com.example.test.Repository.Impl;

import com.example.test.Entity.Impl.TaskEntity;
import com.example.test.Entity.Task;
import com.example.test.Model.ModifyTaskModel;
import com.example.test.Model.TaskModel;
import com.example.test.Repository.TaskRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий задач.
 */
@Repository
public class JPATaskRepository implements TaskRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public boolean createTask(TaskModel task) {
        em.persist(new TaskEntity(task.getTitle(), task.getDescription(), task.getTime(), task.getStatus()));
        em.flush();
        return true;
    }

    @Override
    public Collection<Task> getAllTasks() {
        return em.createNamedQuery(TaskEntity.GET_ALL_TASKS, Task.class).getResultList();
    }

    @Override
    public Optional<Task> getTaskById(long id) {
        return Optional.ofNullable(em.find(TaskEntity.class, id));
    }

    @Override
    @Transactional
    public boolean modifyTask(long id, ModifyTaskModel modifyTask) {
        em.merge(new TaskEntity(id, modifyTask.getTitle(), modifyTask.getDescription(), modifyTask.getTime(), modifyTask.getStatus(), 0));
        em.flush();
        return true;
    }

    @Override
    @Transactional
    public boolean deleteTaskById(long id) {
        em.remove(em.find(TaskEntity.class, id));
        em.flush();
        return true;
    }

    @Override
    @Transactional
    public boolean assignWorkerToTask(long taskId, long workerId) {
        em.createNamedQuery(TaskEntity.ASSIGN_PERFORMER)
          .setParameter("taskId", taskId)
          .setParameter("workerId", workerId)
          .executeUpdate();
        return true;
    }
}
