package com.example.discord.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.discord.entity.DirectMessage;

@Repository
public interface DirectMessageRepository extends JpaRepository<DirectMessage, Long> {

    @Query(value = "SELECT " +
            "dm.id AS message_id, " +
            "dm.sender_id, " +
            "dm.receiver_id, " +
            "dm.message_text, " +
            "u1.username AS sender_name, " +
            "dm.sent_at " +
            "FROM direct_messages dm " +
            "LEFT JOIN users u1 ON u1.id = dm.sender_id " +
            "WHERE (dm.sender_id = :sender_id OR dm.receiver_id = :sender_id) " +
            "AND (dm.receiver_id = :receiver_id OR dm.sender_id = :receiver_id) " +
            "AND dm.message_text IS NOT NULL", nativeQuery = true)
    List<Object[]> getByReceiverIdandSenderId(@Param("sender_id") long sender_id,
            @Param("receiver_id") long receiver_id);

    @Query(value = "SELECT " +
            "    dm.id AS request_id, " +
            "    CASE " +
            "        WHEN dm.sender_id = :userId THEN u2.username " +
            "        ELSE u1.username " +
            "    END AS other_username, " +
            "    CASE " +
            "        WHEN dm.sender_id = :userId THEN dm.receiver_id " +
            "        ELSE dm.sender_id " +
            "    END AS other_user_id " +
            // " dm.sender_id AS sender_id, " +
            // " dm.receiver_id AS receiver_id " +
            "FROM " +
            "    direct_messages dm " +
            "LEFT JOIN " +
            "    users u1 " +
            "ON " +
            "    u1.id = dm.sender_id " +
            "LEFT JOIN " +
            "    users u2 " +
            "ON " +
            "    u2.id = dm.receiver_id " +
            "WHERE " +
            "    (dm.sender_id = :userId OR dm.receiver_id = :userId)" +
            "and message_text is null", nativeQuery = true)
    List<Object[]> getAllInitiatedChat(@Param("userId") long userId);

    @Query(value = "SELECT message_text from direct_messages WHERE sender_id = :senderId and receiver_id = :receiverId and  message_text is null", nativeQuery = true)
    List<Object[]> getByEmptyMessageText(@Param("senderId") long senderId, @Param("receiverId") long receiverId);
}
