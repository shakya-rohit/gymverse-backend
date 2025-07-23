package com.gymverse.backend.controller;

import com.gymverse.backend.model.Trainer;
import com.gymverse.backend.service.TrainerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainers")
@CrossOrigin
public class TrainerController {

    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PostMapping
    public Trainer create(@RequestBody Trainer trainer) {
        return trainerService.createTrainer(trainer);
    }

    @GetMapping("/{id}")
    public Trainer get(@PathVariable String id) {
        return trainerService.getTrainerById(id);
    }

    @GetMapping
    public List<Trainer> getAll() {
        return trainerService.getAllTrainers();
    }

    @PutMapping("/{id}")
    public Trainer update(@PathVariable String id, @RequestBody Trainer trainer) {
        return trainerService.updateTrainer(id, trainer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        trainerService.deleteTrainer(id);
    }
}