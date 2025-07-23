package com.gymverse.backend.service.impl;

import com.gymverse.backend.model.MembershipPlan;
import com.gymverse.backend.repository.MembershipPlanRepository;
import com.gymverse.backend.service.MembershipPlanService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MembershipPlanServiceImpl implements MembershipPlanService {

    private final MembershipPlanRepository repository;

    public MembershipPlanServiceImpl(MembershipPlanRepository repository) {
        this.repository = repository;
    }

    @Override
    public MembershipPlan create(MembershipPlan plan) {
        plan.setPlanId(UUID.randomUUID().toString());
        return repository.save(plan);
    }

    @Override
    public List<MembershipPlan> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<MembershipPlan> getById(String id) {
        return repository.findById(id);
    }

    @Override
    public MembershipPlan update(String id, MembershipPlan plan) {
        plan.setPlanId(id);
        return repository.save(plan);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}