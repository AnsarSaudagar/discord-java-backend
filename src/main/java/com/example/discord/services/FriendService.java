package com.example.discord.services;

import com.example.discord.entity.Friend;
import com.example.discord.repositories.FriendRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

//    @Transactional
//    public List<?> getPendingRequests(long currentUserId){
//        List<?> requests = friendRepository.findUserRequests(currentUserId);
//        return requests;
//    }

    @Transactional
    public List<Map<String, Object>> getPendingRequests(long id) {
        List<Object[]> rawResults = friendRepository.findUserRequests(id);
        List<Map<String, Object>> result = new ArrayList<>();

        for (Object[] row : rawResults) {
            Map<String, Object> map = new HashMap<>();
            map.put("requestId", row[0]);
            map.put("username", row[1]);
            map.put("senderId", row[2]);
            map.put("receiverId", row[3]);
            map.put("displayName", row[4]);
            map.put("status", row[5]);
            result.add(map);
        }

        return result;
    }

    @Transactional
    public int acceptFriend(String id,Friend.FriendshipStatus status){
        int friend = friendRepository.changeFriendStatus(status, id);

        return friend;
    }

    @Transactional
    public int deleteFriend(long id){
        return friendRepository.deleteFriendById(id);
    }

}
