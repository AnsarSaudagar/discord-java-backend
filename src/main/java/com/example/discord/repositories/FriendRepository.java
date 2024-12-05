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
    @Query(value = "SELECT f.id AS request_id, " +
            "CASE " +
            "    WHEN f.user_id1 = :id THEN u2.username " +
            "    ELSE u1.username " +
            "END AS other_username, " +
            "f.user_id1 AS sender_id, " +
            "f.user_id2 AS receiver_id, " +
            "CASE " +
            "    WHEN f.user_id1 = :id THEN u2.display_name " +
            "    ELSE u1.display_name " +
            "END AS other_display_name, " +
            "f.status " +
            "FROM friends f " +
            "LEFT JOIN users u1 ON u1.id = f.user_id1 " +
            "LEFT JOIN users u2 ON u2.id = f.user_id2 " +
            "WHERE (f.user_id1 = :id OR f.user_id2 = :id)",
            nativeQuery = true)
    List<Object[]> findUserRequests(@Param("id") long id);

    @Modifying
    @Query(value = "UPDATE friends SET status = :status WHERE id = :id", nativeQuery = true)
    int changeFriendStatus(@Param("status") Friend.FriendshipStatus status, @Param("id") String id);

    @Modifying
    @Query(value = "DELETE FROM friends WHERE id = :id", nativeQuery = true)
    int deleteFriendById(@Param("id") long id);

}
