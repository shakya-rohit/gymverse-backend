package com.gymverse.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gymverse.backend.model.Member;
import com.gymverse.backend.service.MemberService;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<Member> create(@RequestHeader("X-Tenant-ID") String tenantId, @RequestBody Member member) {
        // tenantId is assumed to be embedded inside the Member object during creation
        return ResponseEntity.ok(memberService.createMember(tenantId, member));
    }

    @GetMapping
    public ResponseEntity<List<Member>> getAll(@RequestHeader("X-Tenant-ID") String tenantId) {
        return ResponseEntity.ok(memberService.getAllMembers(tenantId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getById(@RequestHeader("X-Tenant-ID") String tenantId,
                                          @PathVariable String id) {
        return ResponseEntity.ok(memberService.getMemberById(tenantId, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> update(@RequestHeader("X-Tenant-ID") String tenantId,
                                         @PathVariable String id,
                                         @RequestBody Member member) {
        return ResponseEntity.ok(memberService.updateMember(tenantId, id, member));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@RequestHeader("X-Tenant-ID") String tenantId,
                                       @PathVariable String id) {
        memberService.deleteMember(tenantId, id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/renew")
    public ResponseEntity<Member> renewMembership(@RequestHeader("X-Tenant-ID") String tenantId,
                                                  @PathVariable String id) {
        return ResponseEntity.ok(memberService.renewMembership(tenantId, id));
    }
}