package com.banking.garantiintegrationservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class GarantiAuthService {
    private final RestClient garantiRestClient; // Inject the RestClient bean configured for Garanti API

    @Value("${garanti.api.client-id}")
    private String clientId;

    @Value("${garanti.api.client-secret}")
    private String clientSecret;

    public String getAccessToken() {
        System.out.println("Requesting access token from Garanti API...");

        // Documentation specifies that we need to send these parameters in the body as
        // form data
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "client_credentials");
        formData.add("client_id", clientId);
        formData.add("client_secret", clientSecret);
        formData.add("scope", "oob");

        System.out.println("Client ID: " + clientId);
        // Getting the access token from Garanti API
        Map<String, Object> response = garantiRestClient.post()
                .uri("https://apis.garantibbva.com.tr/auth/oauth/v2/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(formData)
                .retrieve()
                .body(Map.class);

        // Extracting the "access_token" value from the returned JSON
        String token = (String) response.get("access_token");
        System.out.println("Received access token: " + token);
        return token;
    }
}
