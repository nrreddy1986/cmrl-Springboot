package com.shellinfo.demo.model.dto;

import com.shellinfo.demo.model.User;
import lombok.Data;

@Data
public class UserDetailsDto {

    private String name;
    private String mobileNumber;
    private String email;
    private String occupation;
    private String dob;
    private String gender;
    private boolean disability;
    private boolean verified;

    public static UserDetailsDto from(User user) {
        UserDetailsDto dto = new UserDetailsDto();
        dto.setName(user.getName());
        dto.setMobileNumber(user.getMobileNumber());
        dto.setEmail(user.getEmail());
        dto.setOccupation(user.getOccupation());
        dto.setDob(user.getDob());
        dto.setGender(user.getGender());
        dto.setDisability(user.isDisability());
        dto.setVerified(user.isVerified());
        return dto;
    }
}


