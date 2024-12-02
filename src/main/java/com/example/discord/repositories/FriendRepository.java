package com.example.discord.repositories;

import com.example.discord.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {

//    // Find friendships for a specific user
//    List<Friend> findByUser1UserIdOrUser2UserId(Long userId1, Long userId2);
//
//    // Find specific friendship between two users
//    Friend findByUser1UserIdAndUser2UserId(Long userId1, Long userId2);
//
//    // Find pending friendship requests for a user
//    List<Friend> findByUser2UserIdAndStatus(Long userId, Friend.FriendshipStatus status);
}
