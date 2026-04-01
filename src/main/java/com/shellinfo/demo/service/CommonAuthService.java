package com.shellinfo.demo.service;

import com.shellinfo.demo.exception.TokenExpiredException;
import com.shellinfo.demo.model.CommonUser;
import com.shellinfo.demo.model.GoogleUserInfo;
import com.shellinfo.demo.model.dto.TokenResponse;
import com.shellinfo.demo.model.dto.UpdateProfileRequest;
import com.shellinfo.demo.repository.CommonUserRepository;
import com.shellinfo.demo.utils.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommonAuthService {

    private final CommonUserRepository CommonUserRepository;
    private final JwtUtil jwtUtil;
    private final GoogleService googleService;

    // 🔹 Send OTP
    public String sendOtp(String mobile) {

        CommonUser CommonUser = CommonUserRepository.findByMobileNumber(mobile)
                .orElseGet(() -> {
                    CommonUser u = new CommonUser();
                    u.setMobileNumber(mobile);
                    return u;
                });

        String otp = generateOtp();

        CommonUser.setOtp(otp);
        CommonUserRepository.save(CommonUser);
        return otp;
//        System.out.println("OTP: " + otp); // replace with SMS/WhatsApp
    }

    // 🔹 Verify OTP Login
    public String verifyOtp(String mobile, String otp) {

        CommonUser CommonUser = CommonUserRepository.findByMobileNumber(mobile)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!otp.equals(CommonUser.getOtp())) {
            throw new RuntimeException("Invalid OTP");
        }

        CommonUser.setMobileVerified(true);
        CommonUser.setOtp(null);
        CommonUserRepository.save(CommonUser);

        return jwtUtil.generateToken(CommonUser.getPublicId());
    }

    // 🔹 Verify OTP Login
    public TokenResponse verifyOtpNew(String mobile, String otp) {

        CommonUser CommonUser = CommonUserRepository.findByMobileNumber(mobile)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!otp.equals(CommonUser.getOtp())) {
            throw new RuntimeException("Invalid OTP");
        }

        CommonUser.setMobileVerified(true);
        CommonUser.setOtp(null);
        CommonUserRepository.save(CommonUser);

        return jwtUtil.generateTokens(CommonUser.getPublicId());
    }

    // 🔹 Google Login
    public TokenResponse googleLogin(String idToken) {

        GoogleUserInfo info = googleService.verifyToken(idToken);

        CommonUser CommonUser = CommonUserRepository.findByGoogleId(info.getGoogleId())
                .orElseGet(() ->
                        CommonUserRepository.findByEmail(info.getEmail())
                                .orElse(null)
                );

        if (CommonUser == null) {
            CommonUser = new CommonUser();
        }

        CommonUser.setGoogleId(info.getGoogleId());
        CommonUser.setEmail(info.getEmail());
        CommonUser.setName(info.getName());
        CommonUser.setProfileImage(info.getPicture());

        CommonUserRepository.save(CommonUser);

        return jwtUtil.generateTokens(CommonUser.getPublicId());
    }

    // 🔹 Link Mobile after Google login
    public CommonUser linkMobile(HttpServletRequest request, String mobile) {

        CommonUser currentUser = getCurrentUser(request);

        // 🔥 Check if mobile already exists
        Optional<CommonUser> existingUser =
                CommonUserRepository.findByMobileNumber(mobile);

        if (existingUser.isPresent() &&
                !existingUser.get().getId().equals(currentUser.getId())) {

            throw new RuntimeException("Mobile number already linked to another account");
        }

        currentUser.setMobileNumber(mobile);
        return CommonUserRepository.save(currentUser);
    }

    // 🔹 Update Profile
    public CommonUser updateProfile(HttpServletRequest request, UpdateProfileRequest req) {

        CommonUser commonUser = getCurrentUser(request);
        commonUser.setName(req.getName());
        commonUser.setGender(req.getGender());
        commonUser.setDob(req.getDob());
        commonUser.setOccupation(req.getOccupation());
        commonUser.setDisability(req.isDisability());
        commonUser.setTcAgreed(req.isTcAgreed());

        return CommonUserRepository.save(commonUser);
    }

    // 🔹 Get Profile
    public CommonUser getProfile(HttpServletRequest request) {
        return getCurrentUser(request);
    }

    public CommonUser getCurrentUser(HttpServletRequest request) {
        String publicId = getUserIdFromRequest(request);
        return CommonUserRepository.findByPublicId(publicId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public String getUserIdFromRequest(HttpServletRequest request) {

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            throw new TokenExpiredException("Access token missing or invalid");
        }

        String token = header.substring(7);

        try {
            return jwtUtil.extractUserId(token);
        } catch (ExpiredJwtException e) {
            throw new TokenExpiredException("Access token expired");
        }
    }

    public TokenResponse refreshToken(String refreshToken) {
        try {
            String userId = jwtUtil.extractUserId(refreshToken);
//            String newAccessToken = jwtUtil.generateAccessToken(userId);
//            return new TokenResponse(newAccessToken, refreshToken);
            return jwtUtil.generateTokens(userId);
        } catch (ExpiredJwtException e) {
            throw new TokenExpiredException("Refresh token expired");
        }
    }

    public String generateOtp() {
        SecureRandom random = new SecureRandom();
        return String.format("%06d", random.nextInt(1000000));
    }

}
