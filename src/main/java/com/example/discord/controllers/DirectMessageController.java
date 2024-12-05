package com.example.discord.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.discord.dtos.MessageDto;
import com.example.discord.dtos.RegisterUserDto;
import com.example.discord.entity.DirectMessage;
import com.example.discord.services.DirectMessageService;
import com.example.discord.shared.SharedUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/dm")
@RestController
public class DirectMessageController {
    @Autowired
    private DirectMessageService directMessageService;

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody MessageDto messageDto) {
        Long senderId = SharedUtil.getUser().getId();

        System.out.println("ansar hussain");
        System.out.println(messageDto.toString());
        DirectMessage dm = directMessageService.sendDirectMessage(senderId, messageDto.getReceiver_id(),
                messageDto.getMessageText());

        return ResponseEntity.ok(dm);

    }
}
