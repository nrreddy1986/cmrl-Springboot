package com.shellinfo.demo.service;

import com.shellinfo.demo.model.LocationUpdateRequest;
import com.shellinfo.demo.model.PartnerLocation;
import com.shellinfo.demo.repository.PartnerLocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final PartnerLocationRepository repository;

    public void updateLocation(LocationUpdateRequest req){

        PartnerLocation location = new PartnerLocation();

        location.setPartnerId(req.getPartnerId());
        location.setLatitude(req.getLatitude());
        location.setLongitude(req.getLongitude());
        location.setUpdatedAt(LocalDateTime.now());

        repository.save(location);
    }
}