package com.shellinfo.demo.service;

import com.shellinfo.demo.model.CommonUser;
import com.shellinfo.demo.model.dto.ChatListDto;
import com.shellinfo.demo.model.entity.message.Message;
import com.shellinfo.demo.repository.CommonUserRepository;
import com.shellinfo.demo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private CommonUserRepository commonUserRepository;

    /**
     * ✅ Save message
     */
    public Message saveMessage(Message message) {
        message.setCreatedAt(LocalDateTime.now());
        message.setRead(false);
        return messageRepository.save(message);
    }

    /**
     * ✅ Get chat between 2 users
     */
    public List<Message> getChat(Long user1, Long user2) {
        return messageRepository
                .findBySenderIdAndReceiverIdOrReceiverIdAndSenderId(
                        user1, user2,
                        user1, user2
                )
                .stream()
                .sorted(Comparator.comparing(Message::getCreatedAt))
                .toList();
    }

    /**
     * ✅ Chat List (last message per user)
     */
    public List<ChatListDto> getChatList(CommonUser commonUser) {

        List<Message> messages =
                messageRepository.findBySenderIdOrReceiverId(commonUser.getId(), commonUser.getId());

        Map<Long, Message> lastMessageMap = new HashMap<>();

        for (Message msg : messages) {

            Long otherUserId = msg.getSenderId().equals(commonUser.getId())
                    ? msg.getReceiverId()
                    : msg.getSenderId();

            Message existing = lastMessageMap.get(otherUserId);

            if (existing == null ||
                    msg.getCreatedAt().isAfter(existing.getCreatedAt())) {

                lastMessageMap.put(otherUserId, msg);
            }
        }

        /// Convert to DTO
        return lastMessageMap.entrySet().stream()
                .map(entry -> {

                    Long otherUserId = entry.getKey();
                    Message message = entry.getValue();

                    return ChatListDto.from(commonUser, message);

                })
                .sorted((a, b) -> b.getTime().compareTo(a.getTime())) // latest first
                .toList();
    }

    /**
     * ✅ Mark messages as read
     */
    public void markAsRead(Long senderId, Long receiverId) {

        List<Message> messages =
                messageRepository.findBySenderIdAndReceiverId(
                        senderId, receiverId
                );

        for (Message msg : messages) {
            msg.setRead(true);
        }

        messageRepository.saveAll(messages);
    }

}