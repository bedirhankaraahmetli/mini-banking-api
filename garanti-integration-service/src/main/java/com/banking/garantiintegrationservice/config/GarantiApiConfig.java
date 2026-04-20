package com.banking.garantiintegrationservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class GarantiApiConfig {

    @Value("${garanti.api.base-url}")
    private String baseUrl;

    @Value("${garanti.api.client-id}")
    private String clientId;

    @Bean
    public RestClient garantiRestClient() {
        return RestClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("X-IBM-Client-Id", clientId) // Garanti Gatewaway API can read our API key from this
                                                            // header
                .defaultHeader("Accept", "application/json") // Garanti API returns JSON responses, so we set this
                                                             // header to indicate that we expect JSON
                .build();
    }
}

// In this configuration class, we define a bean for RestClient that is
// configured to interact with the Garanti API. We read the base URL and client
// ID from the application properties and set them as default headers for all
// requests made using this RestClient. This way, we can easily inject this
// RestClient into our service classes to make API calls to Garanti without
// having to set these headers every time.
