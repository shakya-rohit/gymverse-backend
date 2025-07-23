package com.gymverse.backend.controller;

import com.gymverse.backend.model.Subscription;
import com.gymverse.backend.service.SubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/subscriptions")
@CrossOrigin
public class SubscriptionController {

    private final SubscriptionService service;

    public SubscriptionController(SubscriptionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Subscription> create(@RequestBody Subscription subscription) {
        subscription.setSubscriptionId(UUID.randomUUID().toString());
        return ResponseEntity.ok(service.create(subscription));
    }

    @GetMapping
    public ResponseEntity<List<Subscription>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subscription> getById(@PathVariable String id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<Subscription>> getByMemberId(@PathVariable String memberId) {
        return ResponseEntity.ok(service.getByMemberId(memberId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subscription> update(@PathVariable String id, @RequestBody Subscription subscription) {
        return ResponseEntity.ok(service.update(id, subscription));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}