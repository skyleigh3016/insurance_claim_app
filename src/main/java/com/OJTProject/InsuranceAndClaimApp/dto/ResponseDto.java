package com.OJTProject.InsuranceAndClaimApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
    private String insurance_type;
    private String name;
    private Long id;

//    private String gender;
//    private String street;
//    private String barangay;
//    private String municipality;
//    private String state;
    private String vehicle_type;
    private String vehicle_uses;
    private int age_coverage;
    private int tenure;
    private int monthly_premium;
    private int annual_premium;
    private int amount_insured;

}
