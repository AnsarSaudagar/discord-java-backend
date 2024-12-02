package com.example.discord.controllers;


import com.example.discord.entity.Friend;
import com.example.discord.services.FriendService;
import com.example.discord.shared.SharedUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/friends")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @PostMapping("/request")
    public ResponseEntity<Friend> sendFriendRequest(@RequestParam Long friend_id){
        Long currentUser = SharedUtil.getUser().getId();
        Friend friend = friendService.sendFriendRequest(currentUser, friend_id);
        return ResponseEntity.ok(friend);
    }
}
