package com.gymverse.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gymverse.backend.model.Member;
import com.gymverse.backend.model.MembershipPlan;
import com.gymverse.backend.model.Trainer;
import com.gymverse.backend.repository.MemberRepository;
import com.gymverse.backend.repository.MembershipPlanRepository;
import com.gymverse.backend.service.MemberService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/members")
public class MemberController {
	
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
		this.memberService = memberService;
    }
    
    @PostMapping
    public ResponseEntity<Member> create(@RequestBody Member member) {
        return ResponseEntity.ok(memberService.createMember(member));
    }
    
    @GetMapping
    public ResponseEntity<List<Member>> getAll() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getById(@PathVariable String id) {
        return ResponseEntity.ok(memberService.getMemberById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> update(@PathVariable String id, @RequestBody Member member) {
        return ResponseEntity.ok(memberService.updateMember(id, member));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/renew")
    public ResponseEntity<Member> renewMembership(@PathVariable String id) {
        return ResponseEntity.ok(memberService.renewMembership(id));
    }

}