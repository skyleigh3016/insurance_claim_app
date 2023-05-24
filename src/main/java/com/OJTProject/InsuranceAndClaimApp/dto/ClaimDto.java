package com.OJTProject.InsuranceAndClaimApp.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
@Data
@Builder
public class ClaimDto {
    private  Long id;
    private String claimer;
    private String client;
    private String admin;
    private String document;
    private int claim_amount;
    private String status;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
