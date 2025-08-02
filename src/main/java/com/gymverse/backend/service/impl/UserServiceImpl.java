package com.gymverse.backend.service.impl;

import com.gymverse.backend.model.Gym;
import com.gymverse.backend.model.User;
import com.gymverse.backend.repository.GymRepository;
import com.gymverse.backend.repository.UserRepository;
import com.gymverse.backend.service.UserService;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final GymRepository gymRepository;

    public UserServiceImpl(UserRepository userRepository, GymRepository gymRepository) {
        this.userRepository = userRepository;
        this.gymRepository = gymRepository;
    }

    @Override
    public User register(User user) {
    	Gym gym = new Gym();
    	gym.setContactEmail(user.getEmailId());
    	gym.setGymId(UUID.randomUUID().toString());
    	gym.setGymName(user.getGymName());
    	gym.setOwnerUsername(user.getUsername());
    	
    	gymRepository.save(gym);
    	
    	user.setTenantId(gym.getGymId());
    	user.setRole("admin");
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