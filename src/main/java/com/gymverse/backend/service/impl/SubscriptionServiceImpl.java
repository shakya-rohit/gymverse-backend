package com.gymverse.backend.service.impl;

import com.gymverse.backend.model.Subscription;
import com.gymverse.backend.repository.SubscriptionRepository;
import com.gymverse.backend.service.SubscriptionService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository repository;

    public SubscriptionServiceImpl(SubscriptionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Subscription create(Subscription subscription) {
    	subscription.setSubscriptionId(UUID.randomUUID().toString());
        return repository.save(subscription);
    }

    @Override
    public Optional<Subscription> getById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Subscription> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Subscription> getByMemberId(String memberId) {
        return repository.findByMemberId(memberId);
    }

    @Override
    public Subscription update(String id, Subscription subscription) {
        subscription.setSubscriptionId(id);
        return repository.save(subscription);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}