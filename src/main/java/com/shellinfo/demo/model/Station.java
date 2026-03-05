package com.shellinfo.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "station", uniqueConstraints = {
        @UniqueConstraint(columnNames = "stationUniqueId")
})
@Data
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stationName;
    private String stationShortName;
    private String stationUniqueId;

    private Integer lineId;
    private String lineName;
    private String lineColorCode;

    private Double latitude;
    private Double longitude;

    private String xPosition;
    private String yPosition;

    private String isJunction;

    private LocalDateTime validFromDate;
    private LocalDateTime validToDate;
}