package com.example.discord.controllers;

import com.example.discord.dtos.LoginUserDto;
import com.example.discord.dtos.RegisterUserDto;
import com.example.discord.entity.User;
import com.example.discord.payload.response.LoginResponse;
import com.example.discord.services.AuthenticationService;
import com.example.discord.services.CacheService;
import com.example.discord.services.JwtService;
import java.util.Random;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    private final CacheService cacheService;

    private final String COLOR_KEY = "profile_color_";
    private String[] colorArr = {"#80848E", "#3BA55C", "#FAA81A", "#F47B67", "#ED4245", "#5865F2", "#EB459E", "#404EED"};


    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService, CacheService cacheService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.cacheService = cacheService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto){
        User registeredUser = authenticationService.signup(registerUserDto);
        registeredUser.setProfileColor(getRandomColorOrGetCache(registeredUser.getId()));
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto){
        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser.getEmail(), authenticatedUser.getId());

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

    private String getRandomColorOrGetCache(long user_id){

        if(cacheService.getValue(COLOR_KEY + user_id ) != null){
            return cacheService.getValue(COLOR_KEY + user_id);
        }

        int rnd = new Random().nextInt(colorArr.length);
        return colorArr[rnd];
    } 
}
