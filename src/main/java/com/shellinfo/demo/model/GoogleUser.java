package com.shellinfo.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "google_users")
@Data
public class GoogleUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String googleId;
    private String name;
    private String email;
    private String profileImage;

    private LocalDateTime createdAt = LocalDateTime.now();
}