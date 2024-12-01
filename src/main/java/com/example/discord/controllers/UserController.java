package com.example.discord.controllers;

import com.example.discord.entity.User;
import com.example.discord.shared.SharedUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RestController
public class UserController {

    @GetMapping("/me")
    public ResponseEntity<?> authenticatedUser() {

        User currentUser = SharedUtil.getUser();

        if(currentUser != null){
            return ResponseEntity.ok(currentUser);
        }
        return ResponseEntity.internalServerError().build();
    }
}
