package com.shellinfo.demo.model.entity.message;

import lombok.Data;

@Data
public class MessageRequest {
    private String receiverId; // publicId
    private String content;
}
