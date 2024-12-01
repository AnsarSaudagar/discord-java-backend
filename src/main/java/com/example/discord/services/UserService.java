package com.example.discord.services;

import com.example.discord.entity.User;
import com.example.discord.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow();
    }
}
