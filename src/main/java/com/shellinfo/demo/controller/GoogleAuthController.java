package com.shellinfo.demo.controller;

import com.shellinfo.demo.model.GoogleUser;
import com.shellinfo.demo.model.GoogleUserInfo;
import com.shellinfo.demo.model.dto.GoogleAuthResponse;
import com.shellinfo.demo.model.dto.GoogleLoginRequest;
import com.shellinfo.demo.service.GoogleService;
import com.shellinfo.demo.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class GoogleAuthController {

    private final GoogleService googleService;
    private final JwtUtil jwtUtil;

    @PostMapping("/google")
    public ResponseEntity<GoogleAuthResponse> googleLogin(
            @RequestBody GoogleLoginRequest request) {

        // 1. Verify Google token
        GoogleUserInfo info = googleService.verifyToken(request.getIdToken());

        // 2. Create or fetch user
        GoogleUser user = googleService.findOrCreateUser(info);

        // 3. Generate JWT
        String token = jwtUtil.generateToken(user.getEmail());

        return ResponseEntity.ok(new GoogleAuthResponse(token, user));
    }
}
