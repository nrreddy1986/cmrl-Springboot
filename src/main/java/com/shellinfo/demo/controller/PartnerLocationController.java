package com.shellinfo.demo.controller;

import com.shellinfo.demo.model.LocationUpdateRequest;
import com.shellinfo.demo.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/location")
@RequiredArgsConstructor
public class PartnerLocationController {

    private final LocationService locationService;

    @PostMapping("/update")
    public String updateLocation(@RequestBody LocationUpdateRequest req) {

        locationService.updateLocation(req);

        return "Location updated";
    }
}
