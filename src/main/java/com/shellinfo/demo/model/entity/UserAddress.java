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

    private String addressLine1;
    private String addressLine2;

    private String city;
    private String state;
    private String pinCode;

    private boolean isDefault;
}