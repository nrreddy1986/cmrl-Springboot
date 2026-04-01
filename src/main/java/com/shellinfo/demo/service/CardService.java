package com.shellinfo.demo.service;

import com.shellinfo.demo.model.entity.UserCard;
import com.shellinfo.demo.repository.UserCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    @Autowired
    private UserCardRepository repo;

    /// 🔹 Add Card
    public UserCard addCard(String userId, UserCard card) {

        card.setUserId(userId);

        /// First card → default
        if (repo.findByUserId(userId).isEmpty()) {
            card.setDefault(true);
        }

        /// If new card is default → unset old
        if (card.isDefault()) {
            repo.findByUserIdAndIsDefaultTrue(userId)
                    .ifPresent(old -> {
                        old.setDefault(false);
                        repo.save(old);
                    });
        }

        return repo.save(card);
    }

    /// 🔹 Get Cards
    public List<UserCard> getCards(String userId) {
        return repo.findByUserId(userId);
    }

    /// 🔹 Delete Card
    public void deleteCard(String userId, Long id) {

        UserCard card = repo.findById(id).orElseThrow();

        if (!card.getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized");
        }

        repo.delete(card);
    }

    /// 🔹 Set Default
    public void setDefault(String userId, Long id) {

        UserCard newDefault = repo.findById(id).orElseThrow();

        if (!newDefault.getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized");
        }

        repo.findByUserIdAndIsDefaultTrue(userId)
                .ifPresent(old -> {
                    old.setDefault(false);
                    repo.save(old);
                });

        newDefault.setDefault(true);
        repo.save(newDefault);
    }
}
