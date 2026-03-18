package com.shellinfo.demo.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.shellinfo.demo.model.GoogleUser;
import com.shellinfo.demo.model.GoogleUserInfo;
import com.shellinfo.demo.repository.GoogleUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class GoogleService {

    private static final String WEB_CLIENT_ID =
            "74096825815-24m74l4d42ofbfj4d263nnl778e4n7lp.apps.googleusercontent.com";

    public GoogleUserInfo verifyToken(String idTokenString) {

        try {
            GoogleIdTokenVerifier verifier =
                    new GoogleIdTokenVerifier.Builder(
                            new NetHttpTransport(),
                            JacksonFactory.getDefaultInstance()
                    )
                            .setAudience(Collections.singletonList(WEB_CLIENT_ID))
                            .build();

            System.out.println("Incoming Token: " + idTokenString);
            System.out.println("Expected WEB_CLIENT_ID: " + WEB_CLIENT_ID);

            GoogleIdToken idToken = verifier.verify(idTokenString);

            if (idToken == null) {
                throw new RuntimeException("Token verification failed");
            }

            GoogleIdToken.Payload payload = idToken.getPayload();

            String audience = payload.getAudience().toString();
            System.out.println("Token AUD: " + audience);

            return new GoogleUserInfo(
                    payload.getSubject(),
                    payload.getEmail(),
                    (String) payload.get("name"),
                    (String) payload.get("picture")
            );


        } catch (Exception e) {
            throw new RuntimeException("Invalid Google token");
        }

    }

    @Autowired
    private GoogleUserRepository userRepository;

    public GoogleUser findOrCreateUser(GoogleUserInfo info) {

        return userRepository.findByEmail(info.getEmail())
                .map(existing -> {
                    existing.setName(info.getName());
                    existing.setProfileImage(info.getPicture());
                    return userRepository.save(existing);
                })
                .orElseGet(() -> {
                    GoogleUser user = new GoogleUser();
                    user.setGoogleId(info.getGoogleId());
                    user.setEmail(info.getEmail());
                    user.setName(info.getName());
                    user.setProfileImage(info.getPicture());
                    return userRepository.save(user);
                });
    }
}
