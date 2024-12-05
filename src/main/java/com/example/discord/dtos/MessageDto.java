package com.example.discord.dtos;

public class MessageDto {
    private String messageText;
    private long receiver_id;

    public String getMessageText() {
        return messageText;
    }
    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
    public long getReceiver_id() {
        return receiver_id;
    }
    public void setReceiver_id(long receiver_id) {
        this.receiver_id = receiver_id;
    }
    public MessageDto() {
    }
    public MessageDto(String messageText, long receiver_id) {
        this.messageText = messageText;
        this.receiver_id = receiver_id;
    }

    @Override
    public String toString() {
        return "MessageDto [messageText=" + messageText + ", receiver_id=" + receiver_id + ", getMessage()="
                + getMessageText() + ", getReceiver_id()=" + getReceiver_id() + ", getClass()=" + getClass()
                + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
    }

}
