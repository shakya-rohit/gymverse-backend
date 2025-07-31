package com.gymverse.backend.service;

import com.gymverse.backend.model.Member;

import java.util.List;

public interface MemberService {
    Member createMember(String tenantId, Member member);
    Member getMemberById(String tenantId, String memberId);
    List<Member> getAllMembers(String tenantId);
    Member updateMember(String tenantId, String memberId, Member updated);
    void deleteMember(String tenantId, String memberId);
    Member renewMembership(String tenantId, String memberId);
}