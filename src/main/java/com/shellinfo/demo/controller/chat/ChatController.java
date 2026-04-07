package com.shellinfo.demo.controller.chat;

import com.shellinfo.demo.model.entity.message.Message;
import com.shellinfo.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/send")
    public void sendMessage(Message message) {

        Message saved = messageService.saveMessage(message);

        messagingTemplate.convertAndSend(
                "/topic/messages/" + message.getReceiverId(),
                saved
        );
    }
}
