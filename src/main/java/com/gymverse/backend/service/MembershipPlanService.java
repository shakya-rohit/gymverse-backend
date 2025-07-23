package com.gymverse.backend.service;

import com.gymverse.backend.model.MembershipPlan;

import java.util.List;
import java.util.Optional;

public interface MembershipPlanService {
    MembershipPlan create(MembershipPlan plan);
    List<MembershipPlan> getAll();
    Optional<MembershipPlan> getById(String id);
    MembershipPlan update(String id, MembershipPlan plan);
    void delete(String id);
}