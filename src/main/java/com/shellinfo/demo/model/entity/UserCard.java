package com.shellinfo.demo.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_cards")
@Data
public class UserCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private String cardToken;
    private String last4;
    private String brand;

    private int expiryMonth;
    private int expiryYear;

    private boolean isDefault;
}