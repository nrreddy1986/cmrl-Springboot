package com.shellinfo.demo.model.dto;

import com.shellinfo.demo.model.entity.UserAddress;
import lombok.Data;

@Data
public class UserAddressDto {

    private Long id;
    private String name;
    private String mobile;
    private String addressType;
    private String addressLine1;
    private String addressLine2;
    private String landmark;
    private String city;
    private String state;
    private String country;
    private String countryCode;
    private String pinCode;

    private boolean defaultAddress;

    public static UserAddressDto from(UserAddress userAddress) {
        UserAddressDto dto = new UserAddressDto();
        dto.setId(userAddress.getId());
        dto.setName(userAddress.getName());
        dto.setMobile(userAddress.getMobile());
        dto.setAddressType(userAddress.getAddressType());
        dto.setAddressLine1(userAddress.getAddressLine1());
        dto.setAddressLine2(userAddress.getAddressLine2());
        dto.setLandmark(userAddress.getLandmark());
        dto.setCity(userAddress.getCity());
        dto.setState(userAddress.getState());
        dto.setCountry(userAddress.getCountry());
        dto.setCountryCode(userAddress.getCountryCode());
        dto.setPinCode(userAddress.getPinCode());
        dto.setDefaultAddress(userAddress.isDefaultAddress());
        return dto;
    }
}


