package com.example.discord.controllers;


import com.example.discord.entity.Friend;
import com.example.discord.entity.User;
import com.example.discord.services.FriendService;
import com.example.discord.services.UserService;
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

    @Autowired
    private UserService userService;

    @PostMapping("/request")
    public ResponseEntity<Friend> sendFriendRequest(@RequestParam String username){
        Long currentUserId = SharedUtil.getUser().getId();

        User friend = userService.getUserByUsername(username);
        Long friend_id = friend.getId();

        Friend newFriendRequest = friendService.sendFriendRequest(currentUserId, friend_id);

        return ResponseEntity.ok(newFriendRequest);
    }
}
