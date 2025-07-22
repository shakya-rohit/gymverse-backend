package com.gymverse.backend.controller;

import org.springframework.web.bind.annotation.*;

import com.gymverse.backend.model.Member;
import com.gymverse.backend.repository.MemberRepository;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberRepository repository;

    public MemberController(MemberRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public void create(@RequestBody Member member) {
        repository.save(member);
    }

    @GetMapping("/{id}")
    public Member get(@PathVariable String id) {
        return repository.getById(id);
    }

    @GetMapping
    public List<Member> getAll() {
        return repository.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        repository.delete(id);
    }
}