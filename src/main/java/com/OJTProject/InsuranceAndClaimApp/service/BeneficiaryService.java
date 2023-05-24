package com.OJTProject.InsuranceAndClaimApp.service;

import com.OJTProject.InsuranceAndClaimApp.dto.VehiclesDto;
import org.springframework.stereotype.Service;

@Service
public interface BeneficiaryService {
    void insertBeneficiary(Long userId);

    void insertBeneficiary1(Long userId, String beneficiary, String contact, int age, String address, String beneficiary1, String contact1, int age1, String address1, String beneficiary2, String contact2, int age2, String address2);
}
