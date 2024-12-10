package com.example.discord.services;

import com.example.discord.dtos.LoginUserDto;
import com.example.discord.dtos.RegisterUserDto;
import com.example.discord.entity.User;
import com.example.discord.repositories.UserRepository;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository,
                                 PasswordEncoder passwordEncoder,
                                 AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    /**
     *
     * @param data
     * @return user
     */
    public User signup(RegisterUserDto data){
        User user = new User(
            data.getDisplay_name(),
            data.getUsername(),
            data.getEmail(),
            "{bcrypt}" + passwordEncoder.encode(data.getPassword()),
            data.getDob()
        );
        User savedUser = userRepository.save(user);
        return savedUser;
    }



    public User authenticate(LoginUserDto data){
        User user = userRepository.findByEmail(data.getEmail()).orElseThrow();
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    data.getEmail(),
                    data.getPassword(),
                    user.getAuthorities()
            )
        );

        return user;
    }
}
