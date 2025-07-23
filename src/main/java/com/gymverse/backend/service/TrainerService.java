package com.gymverse.backend.service;

import com.gymverse.backend.model.Trainer;

import java.util.List;

public interface TrainerService {
    Trainer createTrainer(Trainer trainer);
    Trainer getTrainerById(String id);
    List<Trainer> getAllTrainers();
    Trainer updateTrainer(String id, Trainer trainer);
    void deleteTrainer(String id);
}