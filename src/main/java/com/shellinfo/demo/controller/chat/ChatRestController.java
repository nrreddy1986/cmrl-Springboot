package com.shellinfo.demo.controller.chat;

import com.shellinfo.demo.model.ApiResponse;
import com.shellinfo.demo.model.CommonUser;
import com.shellinfo.demo.model.dto.ChatListDto;
import com.shellinfo.demo.model.dto.MessageListDto;
import com.shellinfo.demo.model.entity.message.MessageList;
import com.shellinfo.demo.repository.CommonUserRepository;
import com.shellinfo.demo.service.CommonAuthService;
import com.shellinfo.demo.service.MessageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
public class ChatRestController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private CommonAuthService authService;

    @Autowired
    private CommonUserRepository commonUserRepository;

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<MessageListDto>> getChat(HttpServletRequest request, @PathVariable String userId) {

        String currentUserId = authService.getUserIdFromRequest(request);

        CommonUser currentCommonUser = commonUserRepository.findByPublicId(currentUserId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        CommonUser commonUser = commonUserRepository.findByPublicId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        MessageListDto messageList = messageService.getChat(currentCommonUser, commonUser);
        return ResponseEntity.ok(
                ApiResponse.success("Success", messageList));

    }

    @GetMapping("/chat-list")
    public ResponseEntity<ApiResponse<ChatListDto>> getChatList(HttpServletRequest request) {

        String currentUserId = authService.getUserIdFromRequest(request);

        CommonUser currentCommonUser = commonUserRepository.findByPublicId(currentUserId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ChatListDto chatListDto = messageService.getChatList(currentCommonUser);

        int noRecords = chatListDto.getChatList().size();
        return ResponseEntity.ok(
                ApiResponse.success(noRecords + " records found", chatListDto)
        );
    }
}