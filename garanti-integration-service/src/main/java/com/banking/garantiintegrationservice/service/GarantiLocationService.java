package com.banking.garantiintegrationservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.banking.garantiintegrationservice.dto.GarantiAtmResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GarantiLocationService {

        private final RestClient garantiRestClient; // Inject the RestClient bean configured for Garanti API
        private final GarantiAuthService authService; // Inject the AuthService to get access tokens

        public GarantiAtmResponse getNearbyAtms(String latitude, String longitude) {
                // 1. Get an access token using the AuthService
                String accessToken = authService.getAccessToken();

                System.out.println(
                                "Making API call to Garanti to get nearby ATMs with lat: " + latitude + " and lon: "
                                                + longitude);

                // 2. Use the token to call the Garanti API for nearby ATMs
                return garantiRestClient.get()
                                .uri(uriBuilder -> uriBuilder
                                                .path("/branch/v1/getunitinfo")
                                                .queryParam("latitude", latitude)
                                                .queryParam("longitude", longitude)
                                                .queryParam("unitType", "C")
                                                .queryParam("distance", 5)
                                                .build())
                                .header("Authorization", "Bearer " + accessToken) // Set the Bearer token in the
                                                                                  // Authorization header
                                .retrieve()
                                .body(GarantiAtmResponse.class); // Return the response as a GarantiAtmResponse object
        }
}
