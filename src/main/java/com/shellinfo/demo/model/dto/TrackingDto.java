package com.shellinfo.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TrackingDto {

    private String status;

    private Double latitude;

    private Double longitude;
}
