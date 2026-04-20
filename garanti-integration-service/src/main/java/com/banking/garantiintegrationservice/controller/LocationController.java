package com.banking.garantiintegrationservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banking.garantiintegrationservice.service.GarantiLocationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/locations")
@RequiredArgsConstructor
public class LocationController {

    private final GarantiLocationService locationService;

    @GetMapping("/atms")
    public ResponseEntity<String> findNearbyAtms(
            @RequestParam String lat,
            @RequestParam String lon) {
        String response = locationService.getNearbyAtms(lat, lon);
        return ResponseEntity.ok(response);
    }

}
