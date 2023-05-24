package com.OJTProject.InsuranceAndClaimApp.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class VehicleDto {
    private  Long id;
    @NotEmpty(message = "Insurance Type should not be empty")
    private String insurance_type;
    @NotEmpty(message = "Vehicle Type should not be empty")
    private String vehicle_type;
    @NotEmpty(message = "Vehicle Uses should not be empty")
    private String vehicle_uses;
    //separate function
    //vehicle
    //range for age coverage
    // automatic red error in age coverage.
    //http link ung file na upload.

    @Min(value = 1, message = "minimum tenure is 1")
    @Max(value = 10, message = "maximum tenure is 10")
    private int tenure;
    @Min(value = 1, message = "please input amount annual premium")
    @Max(value = 65000, message = "the annual premium is to high")
    private int annual_premium;
    @Min(value = 1, message = "please input amount monthly premium")
    @Max(value = 6500, message = "the monthly premium is to high")
    private int monthly_premium;
    @Min(value = 1, message = "please input amount insured")
    @Max(value = 5000000, message = "the annual premium is to high")
    private int amount_insured;

    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
