package com.gymverse.backend.service;

import com.gymverse.backend.dto.DashboardStatsResponse;
import com.gymverse.backend.dto.DashboardSummaryResponse;

public interface DashboardService {
    DashboardSummaryResponse getSummary(String tenantId);
    DashboardStatsResponse getStats(String tenantId, String viewType, int year);
}