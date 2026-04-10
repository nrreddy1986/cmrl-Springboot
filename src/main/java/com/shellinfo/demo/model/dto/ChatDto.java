package com.shellinfo.demo.model.dto;

import com.shellinfo.demo.model.CommonUser;
import com.shellinfo.demo.model.entity.message.Message;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatDto {
    private String userId;
    private String name;
    private String mobileNumber;
    private String email;
    private String lastMessage;
    private LocalDateTime time;
    private boolean isRead;

    public static ChatDto from(CommonUser user, Message message) {
        ChatDto dto = new ChatDto();
        dto.setUserId(user.getPublicId());
        dto.setName(user.getName());
        dto.setMobileNumber(user.getMobileNumber());
        dto.setEmail(user.getEmail());
        dto.setLastMessage(message.getContent());
        dto.setTime(message.getCreatedAt());
        dto.setRead(message.isRead());
        return dto;
    }

}