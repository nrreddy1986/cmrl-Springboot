package com.shellinfo.demo.model.dto;

import lombok.Data;

@Data
public class CommonOtpRequest {
    private String mobileNumber;
    private String otp;
}