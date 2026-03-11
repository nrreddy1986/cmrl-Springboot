package com.shellinfo.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;

    private Double pickupLat;
    private Double pickupLng;

    private Double dropLat;
    private Double dropLng;

    private Long partnerId;

    private String status;

    private LocalDateTime createdAt;
}