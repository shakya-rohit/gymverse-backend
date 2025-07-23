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
    public Member createMember(Member member) {
        member.setMemberId(UUID.randomUUID().toString());
        LocalDate joiningDate = LocalDate.now();

        // Fetch plan safely
        Optional<MembershipPlan> planOpt = planRepository.findById(member.getMembershipPlanId());
        if (planOpt.isEmpty()) {
            throw new IllegalArgumentException("Invalid Membership Plan ID: " + member.getMembershipPlanId());
        }

        MembershipPlan plan = planOpt.get();
        LocalDate expiryDate = joiningDate.plusMonths(plan.getDurationInMonths());

        // Set dates
        member.setJoiningDate(joiningDate);
        member.setExpiryDate(expiryDate);

        return memberRepository.save(member);
    }


    @Override
    public Member getMemberById(String memberId) {
        return memberRepository.getById(memberId);
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member updateMember(String id, Member updatedData) {
        Member existingMember = memberRepository.getById(id);
        if (existingMember == null) {
            throw new IllegalArgumentException("Member with id " + id + " not found.");
        }

        // Update fields
        existingMember.setName(updatedData.getName());
        existingMember.setAge(updatedData.getAge());
        existingMember.setStatus(updatedData.getStatus());

        // If user changes the membership plan, update expiryDate
        if (!existingMember.getMembershipPlanId().equals(updatedData.getMembershipPlanId())) {
            existingMember.setMembershipPlanId(updatedData.getMembershipPlanId());

            Optional<MembershipPlan> planOpt = planRepository.findById(updatedData.getMembershipPlanId());
            if (planOpt.isEmpty()) {
                throw new IllegalArgumentException("Invalid Membership Plan ID");
            }

            MembershipPlan plan = planOpt.get();
            LocalDate newExpiry = existingMember.getJoiningDate().plusMonths(plan.getDurationInMonths());
            existingMember.setExpiryDate(newExpiry);
        }

        memberRepository.save(existingMember);
        return existingMember;
    }


    @Override
    public void deleteMember(String memberId) {
        Member existingMember = memberRepository.getById(memberId);
        if (existingMember == null) {
            throw new IllegalArgumentException("Member with id " + memberId + " not found.");
        }

        memberRepository.delete(memberId);
    }


    @Override
    public Member renewMembership(String memberId) {
        Member member = getMemberById(memberId);
        MembershipPlan plan = planRepository.findById(member.getMembershipPlanId())
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        LocalDate newExpiry = (member.getExpiryDate() != null && member.getExpiryDate().isAfter(LocalDate.now()))
                ? member.getExpiryDate().plusMonths(plan.getDurationInMonths())
                : LocalDate.now().plusMonths(plan.getDurationInMonths());

        member.setExpiryDate(newExpiry);
        return memberRepository.save(member);
    }
}
