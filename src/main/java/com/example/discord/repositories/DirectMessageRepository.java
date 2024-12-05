package com.example.discord.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.discord.entity.DirectMessage;

import java.util.List;
import java.util.Optional;

@Repository
public interface DirectMessageRepository extends JpaRepository<DirectMessage, Long> {

    // Find all messages between two users
    List<DirectMessage> findBySenderAndReceiverOrderBySentAtAsc(long sender_id, long receiver_id);

    // Find all unread messages for a specific user (either as sender or receiver)
    List<DirectMessage> findByReceiverAndIsReadFalse(long receiver_id);

    // Find a specific message by its ID
    Optional<DirectMessage> findById(long messageId);

    // Find messages sent by a user
    List<DirectMessage> findBySender(long sender_id);

    // Find messages received by a user
    List<DirectMessage> findByReceiver(long receiver_id);
}
