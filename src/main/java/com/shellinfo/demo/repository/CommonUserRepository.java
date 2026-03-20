package com.shellinfo.demo.repository;

import com.shellinfo.demo.model.CommonUser;
import com.shellinfo.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommonUserRepository extends JpaRepository<CommonUser, Long> {

    Optional<CommonUser> findByMobileNumber(String mobile);

    Optional<CommonUser> findByGoogleId(String googleId);

    Optional<CommonUser> findByEmail(String email);

    Optional<CommonUser> findByPublicId(String publicId);
}