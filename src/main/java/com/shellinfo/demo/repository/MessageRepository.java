package com.shellinfo.demo.repository;

import com.shellinfo.demo.model.entity.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findBySenderIdAndReceiverIdOrReceiverIdAndSenderId(
            String s1, String r1, String s2, String r2);

    List<Message> findBySenderIdOrReceiverId(String userId, String userId1);

    List<Message> findBySenderIdAndReceiverId(String senderId, String receiverId);
}*/
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findBySenderIdAndReceiverIdOrReceiverIdAndSenderId(
            Long s1, Long r1, Long s2, Long r2
    );

    List<Message> findBySenderIdOrReceiverId(Long senderId, Long receiverId);

    List<Message> findBySenderIdAndReceiverId(Long senderId, Long receiverId);
}
