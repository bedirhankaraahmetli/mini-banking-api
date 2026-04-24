package com.banking.garantiintegrationservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banking.garantiintegrationservice.dto.AtmDto;
import com.banking.garantiintegrationservice.dto.GarantiAtmResponse;
import com.banking.garantiintegrationservice.service.GarantiLocationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/locations")
@RequiredArgsConstructor
public class LocationController {

    private final GarantiLocationService locationService;

    @GetMapping("/atms")
    public ResponseEntity<List<AtmDto>> findNearbyAtms(
            @RequestParam("lat") String lat,
            @RequestParam("lon") String lon) {

        // Call the service to get nearby ATMs from Garanti API
        GarantiAtmResponse response = locationService.getNearbyAtms(lat, lon);

        return ResponseEntity.ok(response.getUnitList()); // Return the list of ATMs in the response body
    }

}
