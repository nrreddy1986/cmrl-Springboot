package com.shellinfo.demo.repository;

import com.shellinfo.demo.model.GoogleUser;
import com.shellinfo.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GoogleUserRepository extends JpaRepository<GoogleUser, Long> {

    Optional<GoogleUser> findByEmail(String email);

}
