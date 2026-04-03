package com.shellinfo.demo.model.dto;

import lombok.Data;

@Data
public class UpdateProfileRequest {
    private String name;
    private String mobileNumber;
    private String email;
    private String gender;
    private String dob;
    private String occupation;
    private boolean isDisability;
    private boolean tcAgreed;
}