package com.gymverse.backend.service;

import com.gymverse.backend.model.Subscription;

import java.util.List;
import java.util.Optional;

public interface SubscriptionService {
    Subscription create(Subscription subscription);
    Optional<Subscription> getById(String id);
    List<Subscription> getAll();
    List<Subscription> getByMemberId(String memberId);
    Subscription update(String id, Subscription subscription);
    void delete(String id);
}