package com.gymverse.backend.service.impl;

import com.gymverse.backend.model.Trainer;
import com.gymverse.backend.repository.TrainerRepository;
import com.gymverse.backend.service.TrainerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TrainerServiceImpl implements TrainerService {

    private final TrainerRepository trainerRepository;

    public TrainerServiceImpl(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @Override
    public Trainer createTrainer(String tenantId, Trainer trainer) {
        trainer.setTenantId(tenantId); // set tenant
        trainer.setTrainerId(UUID.randomUUID().toString()); // set ID
        return trainerRepository.save(trainer);
    }

    @Override
    public Trainer getTrainerById(String tenantId, String trainerId) {
        return trainerRepository.getById(tenantId, trainerId);
    }

    @Override
    public List<Trainer> getAllTrainers(String tenantId) {
        return trainerRepository.getAllTrainers(tenantId);
    }

    @Override
    public Trainer updateTrainer(String tenantId, String trainerId, Trainer trainer) {
        trainer.setTenantId(tenantId); // ensure tenant ID is correct
        trainer.setTrainerId(trainerId);
        return trainerRepository.save(trainer);
    }

    @Override
    public void deleteTrainer(String tenantId, String trainerId) {
        trainerRepository.delete(tenantId, trainerId);
    }
}