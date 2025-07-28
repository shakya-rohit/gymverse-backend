package com.gymverse.backend.controller;

import com.gymverse.backend.dto.AuthRequest;
import com.gymverse.backend.dto.AuthResponse;
import com.gymverse.backend.model.User;
import com.gymverse.backend.security.JwtService;
import com.gymverse.backend.service.UserService;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;

    public AuthController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService=jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User userToRegister) {
        if (userService.findByUsername(userToRegister.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        User user = new User();
        user.setUsername(userToRegister.getUsername());
        user.setPassword(userToRegister.getPassword());
        user.setRole(userToRegister.getRole() != null ? userToRegister.getRole() : "USER");

        userService.register(user);
        return ResponseEntity.ok(Map.of("message", "User registered successfully"));
    }

    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        Optional<User> userOpt = userService.authenticate(request.getUsername(), request.getPassword());

        if (userOpt.isPresent()) {
            String token = jwtService.generateToken(userOpt.get());
            AuthResponse response = new AuthResponse(token, userOpt.get().getRole());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

}