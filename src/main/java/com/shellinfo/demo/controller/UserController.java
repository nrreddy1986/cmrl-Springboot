package com.shellinfo.demo.controller;

import com.shellinfo.demo.model.dto.GetOtpRequest;
import com.shellinfo.demo.model.dto.UserDetailsDto;
import com.shellinfo.demo.model.dto.VerifyOtpRequest;
import com.shellinfo.demo.model.ApiResponse;
import com.shellinfo.demo.model.User;
import com.shellinfo.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Map<String, String>>> register(@Valid @RequestBody User user) {

        User savedUser = userService.register(user);

        Map<String, String> data =
                Collections.singletonMap("otp", savedUser.getOtp());

        return ResponseEntity.ok(
                ApiResponse.success("Registration successful", data)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Map<String, String>>> login(
            @Valid @RequestBody GetOtpRequest getOtpRequest) {

        String otp = userService.sendOtp(
                getOtpRequest.getMobileNumber()
        );

        Map<String, String> data =
                Collections.singletonMap("otp", otp);

        return ResponseEntity.ok(
                ApiResponse.success("Valid User. OTP Sent successfully", data)
        );
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<ApiResponse<UserDetailsDto>> verifyOtp(
            @Valid @RequestBody VerifyOtpRequest request) {

        User user = userService.verifyOtp(
                request.getMobileNumber(),
                request.getOtp()
        );

        UserDetailsDto userDetailsDto = UserDetailsDto.from(user);

        return ResponseEntity.ok(
                ApiResponse.success("OTP Verified", userDetailsDto)
        );
    }

    @PostMapping("/request-otp")
    public ResponseEntity<ApiResponse<Map<String, String>>> resendOtp(
            @Valid @RequestBody GetOtpRequest getOtpRequest) {

        String otp = userService.sendOtp(
                getOtpRequest.getMobileNumber()
        );

        Map<String, String> data =
                Collections.singletonMap("otp", otp);

        return ResponseEntity.ok(
                ApiResponse.success("OTP Sent successfully", data)
        );
    }

    @PostMapping("/deleteAccount")
    public ResponseEntity<ApiResponse<Map<String, String>>> deleteAccount(
            @Valid @RequestBody GetOtpRequest request) {

        String message = userService.deleteAccount(
                request.getMobileNumber()
        );

        Map<String, String> data =
                Collections.singletonMap("Action", message);

        return ResponseEntity.ok(
                ApiResponse.success("Account Deleted successfully", data)
        );
    }

    @PostMapping("/updateUser")
    public ResponseEntity<ApiResponse<UserDetailsDto>> updateUser(@Valid @RequestBody User user) {

        User savedUser = userService.updateUser(user);

        UserDetailsDto userDetailsDto = UserDetailsDto.from(savedUser);

        return ResponseEntity.ok(
                ApiResponse.success("User details fetched", userDetailsDto));
    }

    @PostMapping("/getUserDetails")
    public ResponseEntity<ApiResponse<UserDetailsDto>> getUserDetails(@RequestBody User _user) {
        User user = userService.getUserDetails(_user.getMobileNumber());
        UserDetailsDto userDetailsDto = UserDetailsDto.from(user);

        return ResponseEntity.ok(
                ApiResponse.success("User details fetched", userDetailsDto));
    }

}

