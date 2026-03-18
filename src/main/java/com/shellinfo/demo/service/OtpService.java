package com.shellinfo.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class OtpService {

    private final WhatsAppService whatsAppService;

    public String sendOtp(String mobile) {

        String otp = generateOtp();

        whatsAppService.sendOtp(mobile, otp);

        return otp;
    }

    private String generateOtp() {
        return String.valueOf(100000 + new Random().nextInt(900000));
    }
}
