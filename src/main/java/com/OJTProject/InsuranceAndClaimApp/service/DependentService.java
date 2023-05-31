package com.OJTProject.InsuranceAndClaimApp.service;

import com.OJTProject.InsuranceAndClaimApp.dto.DependentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DependentService {
    void insertDependent(Long userId, String dependent, String contact3, int age3, String address3, String dependent1, String contact4, int age4, String address4, String dependent2, String contact5, int age5, String address5);

    List<DependentDto> findUserInsurance(Long userId);
}
