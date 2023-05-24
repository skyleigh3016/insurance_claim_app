package com.OJTProject.InsuranceAndClaimApp.dto;

import java.time.LocalDateTime;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;

//import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class InsuranceDto {
    private  Long id;
//    @NotEmpty(message = "Insurance Type should not be empty")
    private String insurance_type;
//    @NotEmpty(message = "Vehicle Type should not be empty")
    private String vehicle_type;
//    @NotEmpty(message = "Vehicle Uses should not be empty")
    private String vehicle_uses;
    //separate function
    //vehicle
    //range for age coverage
    // automatic red error in age coverage.
    //http link ung file na upload.

//    @Min(value = 18, message = "minimum age is 18")
//    @Max(value = 65, message = "maximum age is 65")
    private int age_coverage;
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
