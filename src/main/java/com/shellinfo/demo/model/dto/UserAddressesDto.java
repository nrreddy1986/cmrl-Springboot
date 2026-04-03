package com.shellinfo.demo.model.dto;

import com.shellinfo.demo.model.entity.UserAddress;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserAddressesDto {
    private List<UserAddressDto> addresses;

    public static UserAddressesDto from(List<UserAddress> userAddresses) {

        UserAddressesDto dto = new UserAddressesDto();

        if (userAddresses == null || userAddresses.isEmpty()) {
            dto.setAddresses(new ArrayList<>());
            return dto;
        }

        dto.setAddresses(
                userAddresses.stream()
                        .map(UserAddressDto::from)
                        .toList()
        );

        return dto;
    }
}
