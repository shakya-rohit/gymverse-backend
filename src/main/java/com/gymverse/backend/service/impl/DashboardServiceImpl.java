package com.gymverse.backend.service.impl;

import com.gymverse.backend.dto.DashboardStatsResponse;
import com.gymverse.backend.dto.DashboardSummaryResponse;
import com.gymverse.backend.model.Member;
import com.gymverse.backend.model.MembershipPlan;
import com.gymverse.backend.repository.MemberRepository;
import com.gymverse.backend.repository.MembershipPlanRepository;
import com.gymverse.backend.service.DashboardService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final MemberRepository memberRepo;
    private final MembershipPlanRepository planRepo;

    public DashboardServiceImpl(MemberRepository memberRepo, MembershipPlanRepository planRepo) {
        this.memberRepo = memberRepo;
        this.planRepo = planRepo;
    }

    @Override
    public DashboardSummaryResponse getSummary() {
        List<Member> members = memberRepo.findAll();
        List<MembershipPlan> plans = planRepo.findAll();

        int totalMembers = members.size();
        int activePlans = plans.size();

        // Create a map of planId to price to avoid repeated lookups
        Map<String, Double> planPriceMap = plans.stream()
            .collect(Collectors.toMap(MembershipPlan::getPlanId, MembershipPlan::getPrice));

        // Sum revenue by mapping each member's planId to price
        double totalRevenue = members.stream()
            .mapToDouble(m -> planPriceMap.getOrDefault(m.getMembershipPlanId(), 0.0))
            .sum();

        return new DashboardSummaryResponse(totalMembers, activePlans, totalRevenue);
    }

    @Override
    public DashboardStatsResponse getStats(String viewType, int year) {
        List<Member> members = memberRepo.findAll();

        List<String> labels;
        int buckets;
        if (viewType.equalsIgnoreCase("quarterly")) {
            labels = List.of("Q1", "Q2", "Q3", "Q4");
            buckets = 4;
        } else {
            labels = Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun",
                                   "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
            buckets = 12;
        }

        int[] newMembers = new int[buckets];
        int[] renewals = new int[buckets];

        for (Member m : members) {
            LocalDate joining = m.getJoiningDate();
            LocalDate expiry = m.getExpiryDate();
            if (joining != null && joining.getYear() == year) {
                int index = viewType.equals("monthly") ? joining.getMonthValue() - 1 : (joining.getMonthValue() - 1) / 3;
                newMembers[index]++;
            }
            if (expiry != null && expiry.getYear() == year) {
                int index = viewType.equals("monthly") ? expiry.getMonthValue() - 1 : (expiry.getMonthValue() - 1) / 3;
                renewals[index]++;
            }
        }

        return new DashboardStatsResponse(labels, Arrays.stream(newMembers).boxed().toList(), Arrays.stream(renewals).boxed().toList());
    }
}