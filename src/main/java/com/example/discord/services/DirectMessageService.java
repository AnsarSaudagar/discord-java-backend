package com.example.discord.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.discord.entity.DirectMessage;
import com.example.discord.repositories.DirectMessageRepository;

@Service
public class DirectMessageService {
    
    @Autowired
    private DirectMessageRepository directMessageRepository;

    public DirectMessage sendDirectMessage(long sender_id, long receiver_id, String message_text){
        DirectMessage dm = new DirectMessage(
        );
        dm.setReceiver(receiver_id);
        dm.setSender(sender_id);
        dm.setMessageText(message_text);
        System.out.println(dm.getMessageText());

        return directMessageRepository.save(dm);
    }
}
