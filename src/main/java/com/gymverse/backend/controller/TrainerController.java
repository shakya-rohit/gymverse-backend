package com.gymverse.backend.controller;

import com.gymverse.backend.model.Trainer;
import com.gymverse.backend.service.TrainerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainers")
@CrossOrigin
public class TrainerController {
	
	private static final Logger logger = LoggerFactory.getLogger(TrainerController.class);

    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PostMapping
    public Trainer create(@RequestHeader("X-Tenant-ID") String tenantId, @RequestBody Trainer trainer) {
        return trainerService.createTrainer(tenantId, trainer);
    }

    @GetMapping("/{id}")
    public Trainer get(@RequestHeader("X-Tenant-ID") String tenantId, @PathVariable String id) {
        return trainerService.getTrainerById(tenantId, id);
    }

    @GetMapping
    public List<Trainer> getAll(@RequestHeader("X-Tenant-ID") String tenantId) {
    	logger.info("fetching all trainers for tenant: {}", tenantId);
        return trainerService.getAllTrainers(tenantId);
    }

    @PutMapping("/{id}")
    public Trainer update(@RequestHeader("X-Tenant-ID") String tenantId, @PathVariable String id, @RequestBody Trainer trainer) {
        return trainerService.updateTrainer(tenantId, id, trainer);
    }

    @DeleteMapping("/{id}")
    public void delete(@RequestHeader("X-Tenant-ID") String tenantId, @PathVariable String id) {
        trainerService.deleteTrainer(tenantId, id);
    }
}