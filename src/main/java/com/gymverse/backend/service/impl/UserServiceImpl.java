package com.gymverse.backend.service.impl;

import com.gymverse.backend.model.User;
import com.gymverse.backend.repository.UserRepository;
import com.gymverse.backend.service.UserService;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        Optional<User> existingUser = userRepository.findByUsername(username);
        if(existingUser.isPresent()) {
        	return existingUser.get();
        }
        return null;
    }
    
    @Override
    public Optional<User> authenticate(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent() && userOpt.get().getPassword().equals(password)) {
            return userOpt;
        }
        return Optional.empty();
    }

}