package com.shellinfo.demo.model.dto;

import lombok.Data;

@Data
public class GetFareRequest {

    private String fromStationId;
    private String toStationId;
    private String ticketType;
    private int numberOfTickers;
}

