package com.OJTProject.InsuranceAndClaimApp.service;

import com.OJTProject.InsuranceAndClaimApp.dto.ClaimDto;
import com.OJTProject.InsuranceAndClaimApp.model.Claim;
import com.OJTProject.InsuranceAndClaimApp.model.PdfFile;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ClaimService {

    List<ClaimDto> findAllClaims();

    Claim saveClaim(ClaimDto claimDto);

    void delete(Long claimId);


    ClaimDto findClaimById(Long claimId);

    Claim updateClaim(ClaimDto claimDto);
   

    Claim saveUserClaim(ClaimDto claimDto, long userId);

    List<ClaimDto> findAllUserClaims(long id);


    long findAllUserClaims1(String email);


}
