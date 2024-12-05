package com.example.discord.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "direct_messages")
public class DirectMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long messageId;

    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "id", nullable = false)
    private long sender_id;

    @Column(name = "receiver_id", nullable = false)
    private long receiver_id;

    @Column(name = "message_text", nullable = false)
    private String messageText;

    @Column(name = "sent_at", nullable = false, updatable = false)
    private LocalDateTime sentAt;

    @Column(name = "is_read", nullable = false)
    private Boolean isRead;

    public DirectMessage() {
    }

    public DirectMessage(long sender, long receiver, String messageText, LocalDateTime sentAt, Boolean isRead) {
        this.sender_id = sender;
        this.receiver_id = receiver;
        this.messageText = messageText;
        this.sentAt = sentAt;
        this.isRead = isRead;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public long getSender() {
        return sender_id;
    }

    public void setSender(long sender) {
        this.sender_id = sender;
    }

    public long getReceiver() {
        return receiver_id;
    }

    public void setReceiver(long receiver) {
        this.receiver_id = receiver;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }
}
