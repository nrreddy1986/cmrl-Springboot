package com.shellinfo.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "delivery_partner")
@Data
public class DeliveryPartner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String mobile;

    private String vehicleNumber;

    private String status;
}
