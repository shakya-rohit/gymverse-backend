package com.gymverse.backend.service;

import java.util.Optional;

import com.gymverse.backend.model.User;

public interface UserService {
    User register(User user);
    User findByUsername(String username);
    Optional<User> authenticate(String username, String password); 
}