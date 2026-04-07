package com.shellinfo.demo.controller.chat;

import com.shellinfo.demo.model.CommonUser;
import com.shellinfo.demo.model.dto.ChatListDto;
import com.shellinfo.demo.model.entity.message.Message;
import com.shellinfo.demo.repository.CommonUserRepository;
import com.shellinfo.demo.repository.MessageRepository;
import com.shellinfo.demo.service.CommonAuthService;
import com.shellinfo.demo.service.MessageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatRestController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageService messageService;

    @Autowired
    private CommonAuthService authService;

    @Autowired
    private CommonUserRepository commonUserRepository;

    @GetMapping("/{userId}")
    public List<Message> getChat(HttpServletRequest request, @PathVariable String userId) {

        String currentUserId = authService.getUserIdFromRequest(request);

        CommonUser currentCommonUser = commonUserRepository.findByPublicId(currentUserId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        CommonUser commonUser = commonUserRepository.findByPublicId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return messageRepository.findBySenderIdAndReceiverIdOrReceiverIdAndSenderId(currentCommonUser.getId(), commonUser.getId(), commonUser.getId(), currentCommonUser.getId());
    }

    @GetMapping("/chat-list")
    public List<ChatListDto> getChatList(HttpServletRequest request) {

        String currentUserId = authService.getUserIdFromRequest(request);

        CommonUser currentCommonUser = commonUserRepository.findByPublicId(currentUserId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return messageService.getChatList(currentCommonUser);
    }
}