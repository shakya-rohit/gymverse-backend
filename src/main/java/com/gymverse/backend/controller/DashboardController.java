package com.gymverse.backend.controller;

import com.gymverse.backend.dto.DashboardStatsResponse;
import com.gymverse.backend.dto.DashboardSummaryResponse;
import com.gymverse.backend.service.DashboardService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin
public class DashboardController {

    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    @GetMapping("/summary")
    public DashboardSummaryResponse getSummary(@RequestHeader("X-Tenant-ID") String tenantId) {
        return service.getSummary(tenantId);
    }

    @GetMapping("/stats")
    public DashboardStatsResponse getStats(
    		@RequestHeader("X-Tenant-ID") String tenantId,
        @RequestParam String viewType,
        @RequestParam int year) {
        return service.getStats(tenantId, viewType, year);
    }
}