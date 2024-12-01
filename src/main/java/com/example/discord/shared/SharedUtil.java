package com.example.discord.shared;

import com.example.discord.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SharedUtil {

    /**
     *
     * @return Currently authenticated User
     */
    public static User getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }
}
