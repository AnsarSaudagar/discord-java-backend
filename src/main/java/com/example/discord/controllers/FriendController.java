package com.example.discord.controllers;


import com.example.discord.dtos.LoginUserDto;
import com.example.discord.entity.Friend;
import com.example.discord.entity.User;
import com.example.discord.services.FriendService;
import com.example.discord.services.UserService;
import com.example.discord.shared.SharedUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friends")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @Autowired
    private UserService userService;

    @PostMapping("/request")
    public ResponseEntity<?> sendFriendRequest(@RequestParam String username){
        Long currentUserId = SharedUtil.getUser().getId();

        User friend = userService.getUserByUsername(username);

        if(friend == null){
            return new ResponseEntity<>("Friend Not available", HttpStatus.NOT_FOUND);
        }

        Long friend_id = friend.getId();

        Friend newFriendRequest = friendService.sendFriendRequest(currentUserId, friend_id);

        return ResponseEntity.ok(newFriendRequest);
    }

    @GetMapping("/request")
    public ResponseEntity<?> getPendingRequests(){
        Long currentUserId = SharedUtil.getUser().getId();
        List<?> requests = friendService.getPendingRequests(currentUserId);

        return ResponseEntity.ok(requests);
    }

    @PatchMapping("/accept-friend")
    public ResponseEntity<?> changeFriendStatus(@RequestParam long id){
        int friend = friendService.acceptFriend(id, Friend.FriendshipStatus.ACCEPTED);

        return ResponseEntity.ok(friend);
    }

    @DeleteMapping("/delete-friend")
    public ResponseEntity<?> deleteFriend(@RequestParam long id){
        int result = friendService.deleteFriend(id);
        return ResponseEntity.ok(result);
    }

}
