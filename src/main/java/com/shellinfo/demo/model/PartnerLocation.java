package com.shellinfo.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name="partner_location")
@Data
public class PartnerLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long partnerId;

    private Double latitude;

    private Double longitude;

    private LocalDateTime updatedAt;
}
