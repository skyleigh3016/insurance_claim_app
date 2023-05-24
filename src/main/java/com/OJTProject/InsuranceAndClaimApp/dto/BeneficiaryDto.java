package com.OJTProject.InsuranceAndClaimApp.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BeneficiaryDto {
    private  Long id;
    private String beneficiary;
    private int age;
    private String contact;
    private String address;
    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;
}
