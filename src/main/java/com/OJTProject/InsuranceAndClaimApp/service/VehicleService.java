package com.OJTProject.InsuranceAndClaimApp.service;

import com.OJTProject.InsuranceAndClaimApp.dto.DependentDto;
import com.OJTProject.InsuranceAndClaimApp.dto.VehiclesDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VehicleService {

    List<VehiclesDto> findUserVehicle(Long userId);
}
