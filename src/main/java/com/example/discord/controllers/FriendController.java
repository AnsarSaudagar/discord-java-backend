package com.example.discord.controllers;

import com.example.discord.entity.Friend;
import com.example.discord.entity.User;
import com.example.discord.services.FriendService;
import com.example.discord.services.UserService;
import com.example.discord.shared.SharedUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@RestController
@RequestMapping("/friends")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @Autowired
    private RestTemplate restTemplate ;

    @Autowired
    private UserService userService;

    @PostMapping("/request")
    public ResponseEntity<?> sendFriendRequest(@RequestParam String username) {
        Long currentUserId = SharedUtil.getUser().getId();

        User friend = userService.getUserByUsername(username);

        if (friend == null) {
            return new ResponseEntity<>("Friend Not available", HttpStatus.NOT_FOUND);
        }

        Long friend_id = friend.getId();
        // socketIOServerRunner.sendMessageToUser(friend_id.toString(), "hello ansar");

        String url = "http://localhost:3001/send-request/" + friend.getId();
        restTemplate.getForEntity(url, String.class);

        Friend newFriendRequest = friendService.sendFriendRequest(currentUserId, friend_id);

        return ResponseEntity.ok(newFriendRequest);
    }

    @GetMapping("/request")
    public ResponseEntity<?> getPendingRequests() {
        Long currentUserId = SharedUtil.getUser().getId();
        List<?> requests = friendService.getPendingRequests(currentUserId);

        return ResponseEntity.ok(requests);
    }

    @PatchMapping("/accept-friend")
    public ResponseEntity<?> changeFriendStatus(@RequestParam String id, @RequestParam long receiverId) {
        int friend = friendService.acceptFriend(id, Friend.FriendshipStatus.ACCEPTED);

        String url = "http://localhost:3001/accept-request/" + receiverId;
        restTemplate.getForEntity(url, String.class);

        return ResponseEntity.ok(friend);
    }

    @DeleteMapping("/delete-friend")
    public ResponseEntity<?> deleteFriend(@RequestParam long id) {
        int result = friendService.deleteFriend(id);
        return ResponseEntity.ok(result);
    }

}
