package com.shellinfo.demo.controller;

import com.shellinfo.demo.service.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final OtpService otpService;

    @PostMapping("/send-otp")
    public String sendOtp(@RequestParam String mobile) {

        String oto = otpService.sendOtp(mobile);

        return "OTP sent successfully";
    }

}
