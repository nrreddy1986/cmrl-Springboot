package com.shellinfo.demo.model.dto;

import com.shellinfo.demo.model.entity.UserAddress;
import lombok.Data;

import java.util.List;

@Data
public class UserAddressesDto {
    private List<UserAddress> addresses;
}
