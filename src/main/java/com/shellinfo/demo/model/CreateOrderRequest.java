package com.shellinfo.demo.model;

import lombok.Data;

@Data
public class CreateOrderRequest {

    private Long customerId;

    private Double pickupLat;
    private Double pickupLng;

    private Double dropLat;
    private Double dropLng;
}
