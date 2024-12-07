package com.example.discord.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.discord.dtos.MessageDto;
import com.example.discord.entity.DirectMessage;
import com.example.discord.services.DirectMessageService;
import com.example.discord.shared.SharedUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/dm")
@RestController
public class DirectMessageController {
    @Autowired
    private DirectMessageService directMessageService;

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody MessageDto messageDto) {
        Long senderId = SharedUtil.getUser().getId();

        if(!directMessageService.checkInitiatedMessage(senderId, messageDto.getReceiver_id())){
            return ResponseEntity.ok(false);
        }

        DirectMessage dm = directMessageService.sendDirectMessage(senderId, messageDto.getReceiver_id(),
                messageDto.getMessageText());

        return ResponseEntity.ok(dm);

    }

    @GetMapping("/get")
    public ResponseEntity<?> getMethodName(@RequestParam long receiver_id) {
        Long senderId = SharedUtil.getUser().getId();

        List<Map<String, Object>> dms = directMessageService.getMessages(senderId, receiver_id);
        return ResponseEntity.ok(dms);
    }

    @GetMapping("/get-chat")
    public ResponseEntity<?> getInitiatedChat() {
        Long senderId = SharedUtil.getUser().getId();


        List<Map<String, Object>> dms = directMessageService.getInitiatedMessages(senderId);
        return ResponseEntity.ok(dms);
    }
    
}
