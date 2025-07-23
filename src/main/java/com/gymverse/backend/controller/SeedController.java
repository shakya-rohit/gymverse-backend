package com.gymverse.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymverse.backend.seed.DataSeeder;

@RestController
@RequestMapping("/api/seed")
public class SeedController {

    private final DataSeeder dataSeeder;

    public SeedController(DataSeeder dataSeeder) {
        this.dataSeeder = dataSeeder;
    }

    @PostMapping
    public ResponseEntity<String> seed() {
        dataSeeder.run();
        return ResponseEntity.ok("Seeding complete.");
    }
}