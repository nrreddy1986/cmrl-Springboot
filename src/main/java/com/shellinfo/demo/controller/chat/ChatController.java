package com.shellinfo.demo.controller.chat;

import com.shellinfo.demo.model.CommonUser;
import com.shellinfo.demo.model.dto.MessageDto;
import com.shellinfo.demo.model.entity.message.Message;
import com.shellinfo.demo.model.entity.message.MessageRequest;
import com.shellinfo.demo.repository.CommonUserRepository;
import com.shellinfo.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class ChatController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private CommonUserRepository commonUserRepository;

    @MessageMapping("/send")
    public void sendMessage(
            MessageRequest request,
            Principal principal
    ) {

        String senderPublicId = principal.getName();

        CommonUser sender = commonUserRepository
                .findByPublicId(senderPublicId)
                .orElseThrow();

        CommonUser receiver = commonUserRepository
                .findByPublicId(request.getReceiverId())
                .orElseThrow();

        Message message = new Message();
        message.setSenderId(sender.getId());      // ✅ Long
        message.setReceiverId(receiver.getId());  // ✅ Long
        message.setContent(request.getContent());

        Message savedMessage = messageService.saveMessage(message);

        MessageDto messageDto = MessageDto.from(
                savedMessage,
                sender.getPublicId(),   // senderPublicId ❗
                receiver.getPublicId()      // receiverPublicId ❗
        );

        messagingTemplate.convertAndSend(
                "/topic/messages/" + receiver.getPublicId(),
                messageDto
        );

    }
}
