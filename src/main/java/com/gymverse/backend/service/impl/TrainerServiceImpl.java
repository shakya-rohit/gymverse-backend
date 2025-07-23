package com.gymverse.backend.service.impl;

import com.gymverse.backend.model.Trainer;
import com.gymverse.backend.repository.TrainerRepository;
import com.gymverse.backend.service.TrainerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerServiceImpl implements TrainerService {

    private final TrainerRepository trainerRepository;

    public TrainerServiceImpl(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @Override
    public Trainer createTrainer(Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    @Override
    public Trainer getTrainerById(String id) {
        return trainerRepository.getById(id);
    }

    @Override
    public List<Trainer> getAllTrainers() {
        return trainerRepository.getAll();
    }

    @Override
    public Trainer updateTrainer(String id, Trainer trainer) {
        trainer.setTrainerId(id);
        return trainerRepository.save(trainer);
    }

    @Override
    public void deleteTrainer(String id) {
        trainerRepository.delete(id);
    }
}