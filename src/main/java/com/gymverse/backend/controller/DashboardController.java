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
    public DashboardSummaryResponse getSummary() {
        return service.getSummary();
    }

    @GetMapping("/stats")
    public DashboardStatsResponse getStats(
        @RequestParam String viewType,
        @RequestParam int year) {
        return service.getStats(viewType, year);
    }
}