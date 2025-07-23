package com.gymverse.backend.controller;

import com.gymverse.backend.model.MembershipPlan;
import com.gymverse.backend.service.MembershipPlanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/membership-plans")
@CrossOrigin(origins = "*")
public class MembershipPlanController {

    private final MembershipPlanService service;

    public MembershipPlanController(MembershipPlanService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MembershipPlan> create(@RequestBody MembershipPlan plan) {
        return ResponseEntity.ok(service.create(plan));
    }

    @GetMapping
    public ResponseEntity<List<MembershipPlan>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MembershipPlan> getById(@PathVariable String id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MembershipPlan> update(@PathVariable String id, @RequestBody MembershipPlan plan) {
        return service.getById(id).map(existing -> {
            return ResponseEntity.ok(service.update(id, plan));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}