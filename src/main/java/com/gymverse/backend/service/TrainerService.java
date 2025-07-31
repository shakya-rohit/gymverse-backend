package com.gymverse.backend.service;

import com.gymverse.backend.model.Trainer;
import java.util.List;

public interface TrainerService {
    Trainer createTrainer(String tenantId, Trainer trainer);
    Trainer getTrainerById(String tenantId, String trainerId);
    List<Trainer> getAllTrainers(String tenantId);
    Trainer updateTrainer(String tenantId, String trainerId, Trainer trainer);
    void deleteTrainer(String tenantId, String trainerId);
}