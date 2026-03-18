package com.shellinfo.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class WhatsAppService {

    @Value("${whatsapp.api.url}")
    private String apiUrl;

    @Value("${whatsapp.phone.number.id}")
    private String phoneNumberId;

    @Value("${whatsapp.access.token}")
    private String token;

    public void sendOtp(String mobile, String otp) {

        RestTemplate restTemplate = new RestTemplate();

        String url = apiUrl + "/" + phoneNumberId + "/messages";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        String body = """
                {
                  "messaging_product": "whatsapp",
                  "to": "%s",
                  "type": "text",
                  "text": {
                    "body": "%s is your verification code. For your security, do not share this code."
                  }
                }
                """.formatted(mobile, otp);

        HttpEntity<String> request = new HttpEntity<>(body, headers);

//        restTemplate.postForObject(url, request, String.class);

        ResponseEntity<String> response =
                restTemplate.postForEntity(url, request, String.class);

        System.out.println("Meta Response: " + response.getBody());
    }
}
