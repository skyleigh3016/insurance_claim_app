package com.OJTProject.InsuranceAndClaimApp.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@Builder
public class VehiclesDto {
    private  Long id;
    private String vehicle_type;
    private String vehicle_model;
    private String register_number;
    private String chassis_number;
    private LocalDate date_register;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;
}
