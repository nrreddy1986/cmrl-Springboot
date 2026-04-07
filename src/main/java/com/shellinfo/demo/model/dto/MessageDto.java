package com.shellinfo.demo.model.dto;

import com.shellinfo.demo.model.entity.message.Message;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Data
public class MessageDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String senderId;
    private String receiverId;

    private String content;

    private LocalDateTime createdAt = LocalDateTime.now();

    private boolean isRead;

    public static MessageDto from(
            Message message,
            String senderPublicId,
            String receiverPublicId
    ) {
        MessageDto dto = new MessageDto();
        dto.setId(message.getId());
        dto.setSenderId(senderPublicId);
        dto.setReceiverId(receiverPublicId);
        dto.setContent(message.getContent());
        dto.setCreatedAt(message.getCreatedAt());
        dto.setRead(message.isRead());
        return dto;
    }
}