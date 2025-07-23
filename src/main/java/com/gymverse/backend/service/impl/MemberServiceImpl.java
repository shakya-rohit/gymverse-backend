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
    	LocalDate joiningDate = LocalDate.now();
    	
		Optional<MembershipPlan> plan = planRepository.findById(member.getMembershipPlanId());
    	MembershipPlan optedPlan = plan.get();
    	LocalDate expiryDate = joiningDate.plusMonths(optedPlan.getDurationInMonths());
    	
    	member.setJoiningDate(joiningDate);
    	member.setExpiryDate(expiryDate);
    	
    	memberRepository.save(member);
        return member;
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
    public Member updateMember(String memberId, Member updated) {
        Member existing = getMemberById(memberId);
        updated.setMemberId(existing.getMemberId()); // preserve original ID
        return memberRepository.save(updated);
    }

    @Override
    public void deleteMember(String memberId) {
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
