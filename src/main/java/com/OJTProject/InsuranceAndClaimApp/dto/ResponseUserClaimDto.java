package com.OJTProject.InsuranceAndClaimApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUserClaimDto {

    private String name;
    private String claimer;
    private String client;
    private String admin;
    private String document;
    private int claim_amount;
    private String status;

}
