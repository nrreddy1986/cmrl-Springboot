package com.shellinfo.demo.controller;

import com.shellinfo.demo.model.ApiResponse;
import com.shellinfo.demo.model.Fare;
import com.shellinfo.demo.model.dto.FareDetailsDto;
import com.shellinfo.demo.model.dto.GetFareRequest;
import com.shellinfo.demo.service.FareService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fare")
public class FareController {

    private final FareService fareService;

    public FareController(FareService fareService) {
        this.fareService = fareService;
    }


    @PostMapping("/getFare")
    public ResponseEntity<ApiResponse<FareDetailsDto>> getFare(
            @Valid @RequestBody GetFareRequest request) {

        Fare fare = fareService.getFare(request.getFromStationId(), request.getToStationId());

        FareDetailsDto fareDetailsDto = FareDetailsDto.from(fare);

        return ResponseEntity.ok(
                ApiResponse.success("Got fare for stations", fareDetailsDto)
        );

    }

}
