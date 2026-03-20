package com.shellinfo.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "common-users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String publicId;

    // 🔐 Auth fields
    @Column(unique = true)
    private String mobileNumber;

    @Column(unique = true)
    private String googleId;

    private String email;

    private String otp;
    private boolean mobileVerified;

    // 👤 Profile
    private String name;
    private String gender;
    private String dob;
    private String occupation;
    private boolean isDisability;
    private boolean tcAgreed;
    private String profileImage;

    private LocalDateTime createdAt = LocalDateTime.now();

    @PrePersist
    public void generatePublicId() {
        this.publicId = UUID.randomUUID().toString();
    }
}