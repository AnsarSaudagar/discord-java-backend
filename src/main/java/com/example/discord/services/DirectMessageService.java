package com.example.discord.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        return directMessageRepository.save(dm);
    }

    public List<Map<String,Object>> getMessages(long currentUserId, long receiverId){
        List<Object[]> dms = directMessageRepository.getByReceiverIdandSenderId(currentUserId, receiverId);

        List<Map<String, Object>> result = new ArrayList<>();

        for (Object[] row : dms) {
            Map<String, Object> map = new HashMap<>();
            map.put("message_id", row[0]);
            map.put("senderId", row[1]);
            map.put("receiverId", row[2]);
            map.put("message_text", row[3]);
            map.put("sent_at", row[4]);
            map.put("is_read", row[5]);
            result.add(map);
        }

        return result;

    }
}
