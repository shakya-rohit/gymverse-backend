package com.gymverse.backend.seed;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.gymverse.backend.model.Member;
import com.gymverse.backend.model.MembershipPlan;
import com.gymverse.backend.model.Subscription;
import com.gymverse.backend.repository.MemberRepository;
import com.gymverse.backend.repository.MembershipPlanRepository;

@Component
public class DataSeeder {

    private final MemberRepository memberRepository;
    private final MembershipPlanRepository planRepository;

    public DataSeeder(MemberRepository memberRepository, MembershipPlanRepository planRepository) {
        this.memberRepository = memberRepository;
        this.planRepository = planRepository;
    }

    public void run() {
        // if (!memberRepository.findAll().isEmpty()) return;

        List<MembershipPlan> plans = planRepository.findAll();
        Random random = new Random();
        String[] names = { "John", "Jane", "Bob", "Alice", "Mike", "Sara", "Tom", "Emma", "David", "Nina" };

        for (int i = 0; i < 100; i++) {
            MembershipPlan plan = plans.get(random.nextInt(plans.size()));
            LocalDate joinDate = LocalDate.of(2024, random.nextInt(12) + 1, random.nextInt(28) + 1);
            LocalDate expiryDate = joinDate.plusMonths(plan.getDurationInMonths());

            Subscription sub = new Subscription();
            sub.setPlanId(plan.getPlanId());
            sub.setPlanId(plan.getPlanId());
            sub.setStartDate(joinDate);
            sub.setEndDate(expiryDate);

            Member member = new Member();
            member.setMemberId(UUID.randomUUID().toString());
            member.setName(names[random.nextInt(names.length)] + " " + (char) (65 + random.nextInt(26)) + ".");
            member.setAge(18 + random.nextInt(40));
            member.setStatus(random.nextBoolean() ? "Active" : "Expired");
            member.setMembershipPlanId(plan.getPlanId());
            member.setJoiningDate(joinDate);
            member.setExpiryDate(expiryDate);

            memberRepository.save(member);
        }

        System.out.println("🌱 Dummy members seeded!");
    }
}