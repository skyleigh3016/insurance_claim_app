package com.OJTProject.InsuranceAndClaimApp.dto;



import com.OJTProject.InsuranceAndClaimApp.model.Insurance;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {
    private Integer id;
    private String insurance_type;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;
    private Insurance insurances;
}
