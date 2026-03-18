package com.shellinfo.demo.model.dto;

import com.shellinfo.demo.model.GoogleUser;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GoogleAuthResponse {
    private String token;
    private GoogleUser user;
}
