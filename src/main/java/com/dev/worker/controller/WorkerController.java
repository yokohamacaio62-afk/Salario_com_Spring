package com.dev.worker.controller;

import com.dev.worker.dto.WorkerIncomeDTO;
import com.dev.worker.entities.Worker;
import com.dev.worker.repositories.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ForkJoinWorkerThread;

@RestController
@RequestMapping(value = "/workers")
public class WorkerController {

    @Autowired
    private WorkerRepository workerRepository;

    @GetMapping(value = "/{id}/income/{year}/{month}")
    public WorkerIncomeDTO getIncome(@PathVariable long id, @PathVariable Integer year, @PathVariable Integer month) {
        Worker worker = workerRepository.findById(id).get();

        return new WorkerIncomeDTO(worker.getName(), worker.getDepartment().getName(), worker.income(year, month));
    }

}
