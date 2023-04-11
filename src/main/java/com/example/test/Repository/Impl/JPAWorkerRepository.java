package com.example.test.Repository.Impl;

import com.example.test.Entity.Impl.TaskEntity;
import com.example.test.Entity.Impl.WorkerEntity;
import com.example.test.Entity.Task;
import com.example.test.Entity.Worker;
import com.example.test.Model.WorkerModel;
import com.example.test.Repository.WorkerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class JPAWorkerRepository implements WorkerRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public boolean createWorker(WorkerModel worker) {
        em.persist(new WorkerEntity(worker.getName(), worker.getPosition(), worker.getAvatar()));
        em.flush();
        return true;
    }

    @Override
    public Collection<Worker> getAllWorkers() {
        return em.createNamedQuery(WorkerEntity.GET_ALL_WORKERS, Worker.class).getResultList();
    }

    @Override
    public Optional<Worker> getWorkerById(long id) {
        return Optional.ofNullable(em.find(WorkerEntity.class, id));
    }

    @Override
    @Transactional
    public boolean modifyWorker(long id, WorkerModel worker) {
        em.merge(new WorkerEntity(id, worker.getName(), worker.getPosition(), worker.getAvatar()));
        em.flush();
        return true;
    }

    @Override
    @Transactional
    public boolean deleteWorkerById(long id) {
        em.remove(em.find(WorkerEntity.class, id));
        em.flush();
        return true;
    }
}
