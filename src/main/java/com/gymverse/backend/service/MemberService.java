package com.gymverse.backend.service;

import com.gymverse.backend.model.Member;

import java.util.List;

public interface MemberService {
    Member createMember(Member member);
    Member getMemberById(String memberId);
    List<Member> getAllMembers();
    Member updateMember(String memberId, Member updated);
    void deleteMember(String memberId);
    Member renewMembership(String memberId);
}