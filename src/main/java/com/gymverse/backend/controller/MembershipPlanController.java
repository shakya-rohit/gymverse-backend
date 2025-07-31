package com.gymverse.backend.controller;

import com.gymverse.backend.model.MembershipPlan;
import com.gymverse.backend.service.MembershipPlanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/membership-plans")
public class MembershipPlanController {

    private final MembershipPlanService service;

    public MembershipPlanController(MembershipPlanService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MembershipPlan> create(
            @RequestHeader("X-Tenant-ID") String tenantId,
            @RequestBody MembershipPlan plan) {
        return ResponseEntity.ok(service.create(tenantId, plan));
    }

    @GetMapping
    public ResponseEntity<List<MembershipPlan>> getAll(
            @RequestHeader("X-Tenant-ID") String tenantId) {
        return ResponseEntity.ok(service.getAll(tenantId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MembershipPlan> getById(
            @RequestHeader("X-Tenant-ID") String tenantId,
            @PathVariable String id) {
        return service.getById(tenantId, id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MembershipPlan> update(
            @RequestHeader("X-Tenant-ID") String tenantId,
            @PathVariable String id,
            @RequestBody MembershipPlan plan) {
        return service.getById(tenantId, id).map(existing -> {
            return ResponseEntity.ok(service.update(tenantId, id, plan));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @RequestHeader("X-Tenant-ID") String tenantId,
            @PathVariable String id) {
        service.delete(tenantId, id);
        return ResponseEntity.noContent().build();
    }
}