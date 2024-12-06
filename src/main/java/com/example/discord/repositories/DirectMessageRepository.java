package com.example.discord.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.discord.entity.DirectMessage;

@Repository
public interface DirectMessageRepository extends JpaRepository<DirectMessage, Long> {

    @Query(value = "SELECT * FROM direct_messages where sender_id=:sender_id and receiver_id=:receiver_id ", nativeQuery = true)
    List<Object[]> getByReceiverIdandSenderId(@Param("sender_id") long sender_id,
            @Param("receiver_id") long receiver_id);
}
