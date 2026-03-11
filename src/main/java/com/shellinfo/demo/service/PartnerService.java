package com.shellinfo.demo.service;

import com.shellinfo.demo.model.DeliveryPartner;
import com.shellinfo.demo.repository.DeliveryPartnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartnerService {

    private final DeliveryPartnerRepository partnerRepository;

    public List<DeliveryPartner> getAvailablePartners(){
        return partnerRepository.findByStatus("AVAILABLE");
    }
}
