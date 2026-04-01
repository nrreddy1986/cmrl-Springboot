package com.shellinfo.demo.repository;

import com.shellinfo.demo.model.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {

    List<UserAddress> findByUserId(String userId);

    Optional<UserAddress> findByUserIdAndIsDefaultTrue(String userId);
}
