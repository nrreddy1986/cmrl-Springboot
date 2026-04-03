package com.shellinfo.demo.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_addresses")
@Data
public class UserAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private String name;
    private String mobile;

    private String addressType;

    private String addressLine1;
    private String addressLine2;
    private String landmark;

    private String city;
    private String state;
    private String country;
    private String countryCode;
    private String pinCode;

    private boolean defaultAddress;
}