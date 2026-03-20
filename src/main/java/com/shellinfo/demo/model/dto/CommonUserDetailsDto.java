package com.shellinfo.demo.model.dto;

import com.shellinfo.demo.model.CommonUser;
import lombok.Data;

@Data
public class CommonUserDetailsDto {

    private String name;
    private String mobileNumber;
    private String email;
    private String occupation;
    private String dob;
    private String gender;
    private String profileImage;
    private boolean disability;

    public static CommonUserDetailsDto from(CommonUser user) {
        CommonUserDetailsDto dto = new CommonUserDetailsDto();

        dto.setName(valueOrEmpty(user.getName()));
        dto.setMobileNumber(valueOrEmpty(user.getMobileNumber()));
        dto.setEmail(valueOrEmpty(user.getEmail()));
        dto.setOccupation(valueOrEmpty(user.getOccupation()));
        dto.setDob(valueOrEmpty(user.getDob()));
        dto.setGender(valueOrEmpty(user.getGender()));
        dto.setProfileImage(valueOrEmpty(user.getProfileImage()));

        dto.setDisability(user.isDisability()); // boolean safe

        return dto;
    }

    private static String valueOrEmpty(String value) {
        return value != null ? value : "";
    }
}


