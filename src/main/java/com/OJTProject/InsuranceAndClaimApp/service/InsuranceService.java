package com.OJTProject.InsuranceAndClaimApp.service;

import java.util.List;

import com.OJTProject.InsuranceAndClaimApp.dto.InsuranceDto;
import com.OJTProject.InsuranceAndClaimApp.model.Insurance;

public interface InsuranceService {
    //simple method find all the insurance
    List<InsuranceDto> findAllInsurance();
    Insurance saveInsurance(InsuranceDto insuranceDto);

    InsuranceDto findInsuranceById(Long insuranceId);


    void updateInsurance(InsuranceDto insurance);

    void delete(Long insuranceId);

    List<InsuranceDto> findAllVehicle();

    void updateVehicle(InsuranceDto insuranceDto);
}
