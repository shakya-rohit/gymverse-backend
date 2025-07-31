package com.gymverse.backend.service;

import com.gymverse.backend.model.MembershipPlan;

import java.util.List;
import java.util.Optional;

public interface MembershipPlanService {
    MembershipPlan create(String tenantId, MembershipPlan plan);
    List<MembershipPlan> getAll(String tenantId);
    Optional<MembershipPlan> getById(String tenantId, String planId);
    MembershipPlan update(String tenantId, String planId, MembershipPlan plan);
    void delete(String tenantId, String planId);
}