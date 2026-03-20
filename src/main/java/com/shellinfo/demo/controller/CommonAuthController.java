package com.shellinfo.demo.controller;

import com.shellinfo.demo.model.ApiResponse;
import com.shellinfo.demo.model.CommonUser;
import com.shellinfo.demo.model.dto.*;
import com.shellinfo.demo.service.CommonAuthService;
import com.shellinfo.demo.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/common-auth")
@RequiredArgsConstructor
public class CommonAuthController {

    private final CommonAuthService authService;
    private final JwtUtil jwtUtil;

    // 📱 Send OTP
    @PostMapping("/send-otp")
    public ResponseEntity<ApiResponse<Map<String, String>>> sendOtp(@Valid @RequestBody GetOtpRequest getOtpRequest) {

        String otp = authService.sendOtp(
                getOtpRequest.getMobileNumber()
        );

        Map<String, String> data =
                Collections.singletonMap("otp", otp);

        return ResponseEntity.ok(
                ApiResponse.success("Valid User. OTP Sent successfully", data)
        );
    }

    // 📱 Verify OTP
    @PostMapping("/verify-otp")
    public ResponseEntity<ApiResponse<Map<String, String>>> verifyOtp(@RequestBody CommonOtpRequest request) {
        String token = authService.verifyOtp(
                request.getMobileNumber(),
                request.getOtp()
        );
        Map<String, String> data =
                Collections.singletonMap("token", token);

        return ResponseEntity.ok(
                ApiResponse.success("OTP verified successfully", data)
        );
    }

    // 🔵 Google Login
    @PostMapping("/google")
    public ResponseEntity<ApiResponse<Map<String, String>>> googleLogin(
            @RequestBody GoogleLoginRequest request) {

        String token = authService.googleLogin(request.getIdToken());
        Map<String, String> data =
                Collections.singletonMap("token", token);
        return ResponseEntity.ok(
                ApiResponse.success("Google login success", data));
    }

    // 🔗 Link Mobile
    @PostMapping("/link-mobile")
    public ResponseEntity<ApiResponse<CommonUserDetailsDto>> linkMobile(
            HttpServletRequest request,
            @RequestParam String mobile) {

        CommonUser commonUser = authService.linkMobile(request, mobile);
        CommonUserDetailsDto commonUserDetailsDto = CommonUserDetailsDto.from(commonUser);
        return ResponseEntity.ok(ApiResponse.success("Mobile linked successfully", commonUserDetailsDto));
    }

    // 👤 Update Profile
    @PutMapping("/profile")
    public ResponseEntity<ApiResponse<CommonUserDetailsDto>> updateProfile(
            HttpServletRequest request,
            @RequestBody UpdateProfileRequest req) {

        CommonUser commonUser = authService.updateProfile(request, req);
        CommonUserDetailsDto commonUserDetailsDto = CommonUserDetailsDto.from(commonUser);
        return ResponseEntity.ok(ApiResponse.success("User Profile Details", commonUserDetailsDto));

    }

    // 👤 Get Profile
    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<CommonUserDetailsDto>> getProfile(HttpServletRequest request) {

        CommonUser commonUser = authService.getProfile(request);
        CommonUserDetailsDto commonUserDetailsDto = CommonUserDetailsDto.from(commonUser);
        return ResponseEntity.ok(ApiResponse.success("User Profile Details", commonUserDetailsDto));
    }
}
