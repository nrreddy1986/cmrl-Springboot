package com.shellinfo.demo.model.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChatListDto {
    private List<ChatDto> chatList;

    public static ChatListDto from(List<ChatDto> userChats) {
        ChatListDto dto = new ChatListDto();

        dto.setChatList(userChats != null ? userChats : new ArrayList<>());

        return dto;
    }
}
