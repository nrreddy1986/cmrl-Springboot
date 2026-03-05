package com.shellinfo.demo.repository;

import com.shellinfo.demo.model.Fare;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FareRepository extends JpaRepository<Fare, Long> {

    Optional<Fare> findByFromStationIdAndToStationId(
            String fromStationId,
            String toStationId
    );
}