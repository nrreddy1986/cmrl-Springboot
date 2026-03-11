package com.shellinfo.demo.model;

import lombok.Data;

@Data
public class LocationUpdateRequest {

    private Long partnerId;

    private Double latitude;

    private Double longitude;
}