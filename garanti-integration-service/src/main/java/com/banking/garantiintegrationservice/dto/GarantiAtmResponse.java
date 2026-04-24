package com.banking.garantiintegrationservice.dto;

import java.util.List;

import lombok.Data;

@Data
public class GarantiAtmResponse {
    private List<AtmDto> unitList;
    private String returnMessage;
    private int returnCode;
}
