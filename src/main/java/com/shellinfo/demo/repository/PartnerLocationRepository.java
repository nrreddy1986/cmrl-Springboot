package com.shellinfo.demo.repository;

import com.shellinfo.demo.model.PartnerLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerLocationRepository extends JpaRepository<PartnerLocation, Long> {

    PartnerLocation findTopByPartnerIdOrderByUpdatedAtDesc(Long partnerId);
}
