package com.dev.worker.repositories;

import com.dev.worker.entities.Department;
import com.dev.worker.entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
}
