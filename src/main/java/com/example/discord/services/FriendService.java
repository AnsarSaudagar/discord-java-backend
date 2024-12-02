package com.example.discord.services;

import com.example.discord.entity.Friend;
import com.example.discord.entity.User;
import com.example.discord.repositories.FriendRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FriendService {

    @Autowired
    private FriendRepository friendRepository;

    @Transactional
    public Friend sendFriendRequest(Long userId1, Long userId2){
//        Optional<Friend> existingFriend = Optional.ofNullable(friendRepository.findByUser1UserIdAndUser2UserId(userId1, userId2));
//
//        if(existingFriend.isPresent()){
//            throw new IllegalStateException("Friendship already exist");
//        }

        Friend friend = new Friend();
        friend.setUser_id1(userId1);
        friend.setUser_id2(userId2);
        return friendRepository.save(friend);
    }
}
