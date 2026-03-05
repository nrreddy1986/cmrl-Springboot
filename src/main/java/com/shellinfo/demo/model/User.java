package com.shellinfo.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "mobileNumber")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @NotBlank(message = "Mobile number is required") // ^[6-9]\d{9}$
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid Mobile number")
    private String mobileNumber;

    @Email(message = "Invalid email format")
    private String email;
    private String occupation;
    private String dob;
    private boolean isDisability;
    private String gender;
    private boolean tcAgreed;
    private String otp;
    private boolean verified;
}
