package com.shellinfo.demo.model.dto;

import lombok.Data;

@Data
public class UpdateUserRequest {

    private String name;
    private String email;
    private String occupation;
    private String dob;
    private String gender;
    private boolean disability;
}

