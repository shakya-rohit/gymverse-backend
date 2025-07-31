package com.gymverse.backend.service.impl;

import com.gymverse.backend.model.Member;
import com.gymverse.backend.model.MembershipPlan;
import com.gymverse.backend.repository.MemberRepository;
import com.gymverse.backend.repository.MembershipPlanRepository;
import com.gymverse.backend.service.MemberService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MembershipPlanRepository planRepository;

    public MemberServiceImpl(MemberRepository memberRepository, MembershipPlanRepository planRepository) {
        this.memberRepository = memberRepository;
        this.planRepository = planRepository;
    }

    @Override
    public Member createMember(String tenantId, Member member) {
    	member.setTenantId(tenantId);
        member.setMemberId(UUID.randomUUID().toString());
        LocalDate joiningDate = LocalDate.now();

        // Validate plan
        Optional<MembershipPlan> planOpt = planRepository.findById(member.getTenantId(), member.getMembershipPlanId());
        if (planOpt.isEmpty()) {
            throw new IllegalArgumentException("Invalid Membership Plan ID: " + member.getMembershipPlanId());
        }

        MembershipPlan plan = planOpt.get();
        LocalDate expiryDate = joiningDate.plusMonths(plan.getDurationInMonths());

        // Set metadata
        member.setJoiningDate(joiningDate);
        member.setExpiryDate(expiryDate);

        return memberRepository.save(member);
    }

    @Override
    public Member getMemberById(String tenantId, String memberId) {
        return memberRepository.getById(tenantId, memberId);
    }

    @Override
    public List<Member> getAllMembers(String tenantId) {
        return memberRepository.findAllByTenantId(tenantId);
    }

    @Override
    public Member updateMember(String tenantId, String memberId, Member updatedData) {
        Member existing = memberRepository.getById(tenantId, memberId);
        if (existing == null) {
            throw new IllegalArgumentException("Member with ID " + memberId + " not found.");
        }

        // Update basic fields
        existing.setName(updatedData.getName());
        existing.setAge(updatedData.getAge());
        existing.setStatus(updatedData.getStatus());

        // Handle plan change
        if (!existing.getMembershipPlanId().equals(updatedData.getMembershipPlanId())) {
            existing.setMembershipPlanId(updatedData.getMembershipPlanId());

            MembershipPlan plan = planRepository.findById(tenantId, updatedData.getMembershipPlanId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Membership Plan ID"));

            LocalDate newExpiry = existing.getJoiningDate().plusMonths(plan.getDurationInMonths());
            existing.setExpiryDate(newExpiry);
        }

        return memberRepository.save(existing);
    }

    @Override
    public void deleteMember(String tenantId, String memberId) {
        Member existing = memberRepository.getById(tenantId, memberId);
        if (existing == null) {
            throw new IllegalArgumentException("Member with ID " + memberId + " not found.");
        }

        memberRepository.delete(tenantId, memberId);
    }

    @Override
    public Member renewMembership(String tenantId, String memberId) {
        Member member = memberRepository.getById(tenantId, memberId);
        if (member == null) {
            throw new IllegalArgumentException("Member not found.");
        }

        MembershipPlan plan = planRepository.findById(tenantId, member.getMembershipPlanId())
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        LocalDate newExpiry = (member.getExpiryDate() != null && member.getExpiryDate().isAfter(LocalDate.now()))
                ? member.getExpiryDate().plusMonths(plan.getDurationInMonths())
                : LocalDate.now().plusMonths(plan.getDurationInMonths());

        member.setExpiryDate(newExpiry);
        return memberRepository.save(member);
    }
}