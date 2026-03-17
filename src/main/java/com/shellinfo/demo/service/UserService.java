package com.shellinfo.demo.service;

import com.shellinfo.demo.exception.MobileAlreadyExistsException;
import com.shellinfo.demo.model.GoogleUser;
import com.shellinfo.demo.model.GoogleUserInfo;
import com.shellinfo.demo.model.User;
import com.shellinfo.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User register(User user) {

        if (userRepository.existsByMobileNumber(user.getMobileNumber())) {
            throw new MobileAlreadyExistsException("Mobile number already registered");
        }

        user.setVerified(false);
        user.setOtp(generateOtp());

        return userRepository.save(user);
    }

    public String login(User _user) {
        // 1. Regex validation check (if not done by @Valid)
        if (!_user.getMobileNumber().matches("^[6-9]\\d{9}$")) {
            throw new RuntimeException("Invalid mobile number format");
        }

        User user = userRepository.findByMobileNumber(_user.getMobileNumber())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getOtp();
    }

    public User verifyOtp(String email, String otp) {
        User user = userRepository.findByMobileNumber(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getOtp().equals(otp)) {
            user.setVerified(true);
            userRepository.save(user);
            return user;
        } else {
            throw new RuntimeException("Invalid OTP");
        }

    }

    public String sendOtp(String mobileNumber) {

        User user = userRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String newOtp = generateOtp();

        user.setOtp(newOtp);
        user.setVerified(false); // optional: reset verification

        userRepository.save(user);   // ✅ VERY IMPORTANT

        return newOtp;
    }

    @Transactional
    public User updateUser(User request) {

        User user = userRepository.findByMobileNumber(request.getMobileNumber())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update only allowed fields
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setOccupation(request.getOccupation());
        user.setDob(request.getDob());
        user.setGender(request.getGender());
        user.setDisability(request.isDisability());

        userRepository.save(user);

        return user;
    }


    public String deleteAccount(String mobileNumber) {

        User user = userRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(user);

        return "Account deleted successfully";
    }

    public User getUserDetails(String mobileNumber) {
        return userRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public String generateOtp() {
        SecureRandom random = new SecureRandom();
        return String.format("%06d", random.nextInt(1000000));
    }

}

