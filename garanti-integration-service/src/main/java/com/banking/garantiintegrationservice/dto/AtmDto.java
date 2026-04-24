package com.banking.garantiintegrationservice.dto;

import lombok.Data;

@Data
public class AtmDto {
    private String name;
    private String unitType; // "ATM" or "Branch"
    private String cityName;
    private String address1;
    private double distance;

}
