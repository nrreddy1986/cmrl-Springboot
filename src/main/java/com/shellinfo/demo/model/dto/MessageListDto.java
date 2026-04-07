package com.shellinfo.demo.model.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MessageListDto {
    private List<MessageDto> messageList;

    public static MessageListDto from(List<MessageDto> userMessages) {
        MessageListDto dto = new MessageListDto();

        dto.setMessageList(userMessages != null ? userMessages : new ArrayList<>());

        return dto;
    }
}
