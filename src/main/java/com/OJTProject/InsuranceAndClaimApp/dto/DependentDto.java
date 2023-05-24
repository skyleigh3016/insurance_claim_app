package com.OJTProject.InsuranceAndClaimApp.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class DependentDto {
    private  Long id;
    private String dependent;
    private int age;
    private String contact;
    private String address;
    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;
}
