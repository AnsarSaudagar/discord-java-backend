package com.example.discord.repositories;

import com.example.discord.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {
    @Query(value = "SELECT f.id as request_id," +
                    " u.username," +
                    " f.user_id1 as sender_id ," +
                    "f.user_id2 as reciever_id," +
                    " u.display_name ,f.status FROM" +
                    " `friends` f LEFT JOIN " +
                    "users u on u.id = f.user_id2 " +
                    "WHERE (f.user_id1 = :id or f.user_id2 = :id)", nativeQuery = true)
    List<Object[]> findUserRequests(@Param("id") long id);

    @Modifying
    @Query(value = "UPDATE friends SET status = :status WHERE id = :id", nativeQuery = true)
    int acceptFriend(@Param("status") Friend.FriendshipStatus status, @Param("id") long id);
}
