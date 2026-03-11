package com.shellinfo.demo.repository;

import com.shellinfo.demo.model.DeliveryPartner;
import com.shellinfo.demo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryPartnerRepository extends JpaRepository<DeliveryPartner, Long> {
    List<DeliveryPartner> findByStatus(String status);
}
