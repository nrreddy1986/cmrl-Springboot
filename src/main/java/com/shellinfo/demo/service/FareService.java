package com.shellinfo.demo.service;

import com.shellinfo.demo.model.Fare;
import com.shellinfo.demo.repository.FareRepository;
import org.springframework.stereotype.Service;

@Service
public class FareService {

    private final FareRepository fareRepository;

    public FareService(FareRepository fareRepository) {
        this.fareRepository = fareRepository;
    }

    public Fare getFare(String fromStationId, String toStationId) {

        return fareRepository
                .findByFromStationIdAndToStationId(fromStationId, toStationId)
                .orElseThrow(() ->
                        new RuntimeException("Fare not found"));
    }
}
