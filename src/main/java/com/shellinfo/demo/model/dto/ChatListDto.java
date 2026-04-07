package com.shellinfo.demo.model.dto;

import com.shellinfo.demo.model.CommonUser;
import com.shellinfo.demo.model.entity.message.Message;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatListDto {
    private String userId;
    private String name;
    private String lastMessage;
    private LocalDateTime time;

    public static ChatListDto from(CommonUser user, Message message) {
        ChatListDto dto = new ChatListDto();
        dto.setUserId(user.getPublicId());
        dto.setName(user.getName());
        dto.setLastMessage(message.getContent());
        dto.setTime(message.getCreatedAt());
        return dto;
    }
}