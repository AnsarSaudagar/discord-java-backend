package com.example.discord.controllers;

import com.example.discord.entity.User;
import com.example.discord.services.UserService;
import com.example.discord.shared.SharedUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public ResponseEntity<?> authenticatedUser() {

        User currentUser = SharedUtil.getUser();

        if(currentUser != null){
            return ResponseEntity.ok(currentUser);
        }
        return ResponseEntity.internalServerError().build();
    }

    @GetMapping("/friend")
    public ResponseEntity<?> getUserByUsername(@RequestParam String username){
        User friend = userService.getUserByUsername(username);

        if(friend != null){
            return ResponseEntity.ok(friend);
        }
        return ResponseEntity.internalServerError().build();
    }
}
