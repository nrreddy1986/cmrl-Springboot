package com.shellinfo.demo.model.entity.message;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MessageList {
    private List<Message> messageList;

    public static MessageList from(List<Message> userMessages) {
        MessageList dto = new MessageList();

        dto.setMessageList(userMessages != null ? userMessages : new ArrayList<>());

        return dto;
    }
}
