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
    public MembershipPlan create(String tenantId, MembershipPlan plan) {
        plan.setTenantId(tenantId);
        plan.setPlanId(UUID.randomUUID().toString());
        return repository.save(plan);
    }

    @Override
    public List<MembershipPlan> getAll(String tenantId) {
        return repository.findAllByTenantId(tenantId);
    }

    @Override
    public Optional<MembershipPlan> getById(String tenantId, String planId) {
        return repository.findById(tenantId, planId);
    }

    @Override
    public MembershipPlan update(String tenantId, String planId, MembershipPlan plan) {
        plan.setTenantId(tenantId);
        plan.setPlanId(planId);
        return repository.save(plan);
    }

    @Override
    public void delete(String tenantId, String planId) {
        repository.deleteById(tenantId, planId);
    }
}