package com.OJTProject.InsuranceAndClaimApp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OJTProject.InsuranceAndClaimApp.dto.InsuranceDto;
import com.OJTProject.InsuranceAndClaimApp.model.Insurance;
import com.OJTProject.InsuranceAndClaimApp.repository.InsuranceRepository;
import com.OJTProject.InsuranceAndClaimApp.service.InsuranceService;
@Service
public class InsuranceServiceImpl implements InsuranceService{

@Autowired
    private InsuranceRepository insuranceRepository;

    public InsuranceServiceImpl(InsuranceRepository insuranceRepository) {
        this.insuranceRepository = insuranceRepository;
    }

    @Override
    public List<InsuranceDto> findAllInsurance() {
        List<Insurance> insurances = insuranceRepository.findByAddLineContains();
        return insurances.stream().map((insurance) -> mapToInsuranceDto(insurance)).collect(Collectors.toList());
    
    }

    @Override
    public Insurance saveInsurance(InsuranceDto insuranceDto) {
        Insurance insurance = mapToInsurance(insuranceDto);
        return insuranceRepository.save(insurance);
    }

    @Override
    public InsuranceDto findInsuranceById(Long insuranceId) {
        Insurance insurance = insuranceRepository.findById(insuranceId).get();
        return mapToInsuranceDto(insurance);
    }

    @Override
    public void updateInsurance(InsuranceDto insuranceDto) {
        Insurance insurance = mapToInsurance(insuranceDto);
        insuranceRepository.save(insurance);
    }

    @Override
    public void delete(Long insuranceId) {
        insuranceRepository.deleteById(insuranceId);
    }

    @Override
    public List<InsuranceDto> findAllVehicle() {
        List<Insurance> insurances = insuranceRepository.findByAddLineContain();
        return insurances.stream().map((insurance) -> mapToInsuranceDto(insurance)).collect(Collectors.toList());
    }

    @Override
    public void updateVehicle(InsuranceDto insuranceDto) {
        Insurance insurance1 = mapToInsurance1(insuranceDto);
        insuranceRepository.save(insurance1);
    }

    private Insurance mapToInsurance1(InsuranceDto insuranceDto) {
        Insurance insurance = Insurance.builder()
                .id(insuranceDto.getId())
                .insurance_type(insuranceDto.getInsurance_type())
                .insurance_type(insuranceDto.getInsurance_type())
                .vehicle_type(insuranceDto.getVehicle_type())
                .vehicle_uses(insuranceDto.getVehicle_uses())
                .age_coverage(insuranceDto.getAge_coverage())
                .tenure(insuranceDto.getTenure())
                .annual_premium(insuranceDto.getAnnual_premium())
                .monthly_premium(insuranceDto.getMonthly_premium())
                .amount_insured(insuranceDto.getAmount_insured())
                .createdOn(insuranceDto.getCreatedOn())
                .updatedOn(insuranceDto .getUpdatedOn())
                .build();
        return insurance;

    }

    private Insurance mapToInsurance(InsuranceDto insurance) {
        Insurance insuranceDto = Insurance.builder()
                .id(insurance.getId())
                .insurance_type(insurance.getInsurance_type())
                .vehicle_type(insurance.getVehicle_type())
                .vehicle_uses(insurance.getVehicle_uses())
                .age_coverage(insurance.getAge_coverage())
                .tenure(insurance.getTenure())
                .annual_premium(insurance.getAnnual_premium())
                .monthly_premium(insurance.getMonthly_premium())
                .amount_insured(insurance.getAmount_insured())
                .createdOn(insurance.getCreatedOn())
                .updatedOn(insurance.getUpdatedOn())
                .build();
        return insuranceDto;
    }


    private InsuranceDto mapToInsuranceDto(Insurance insurance){
        InsuranceDto insuranceDto = InsuranceDto.builder()
        .id(insurance.getId())
        .insurance_type(insurance.getInsurance_type())
        .vehicle_type(insurance.getVehicle_type())
        .vehicle_uses(insurance.getVehicle_uses())
        .age_coverage(insurance.getAge_coverage())
        .tenure(insurance.getTenure())
        .annual_premium(insurance.getAnnual_premium())
        .monthly_premium(insurance.getMonthly_premium())
        .amount_insured(insurance.getAmount_insured())
        .createdOn(insurance.getCreatedOn())
        .updatedOn(insurance.getUpdatedOn())
        .build();
return insuranceDto;
    }

   
    
}
