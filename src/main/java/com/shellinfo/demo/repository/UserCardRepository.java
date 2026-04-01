package com.shellinfo.demo.repository;

import com.shellinfo.demo.model.entity.UserCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserCardRepository extends JpaRepository<UserCard, Long> {

    List<UserCard> findByUserId(String userId);

    Optional<UserCard> findByUserIdAndIsDefaultTrue(String userId);
}
