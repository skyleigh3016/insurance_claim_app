package com.OJTProject.InsuranceAndClaimApp.service.impl;

import com.OJTProject.InsuranceAndClaimApp.dto.ClaimDto;
import com.OJTProject.InsuranceAndClaimApp.model.Claim;
import com.OJTProject.InsuranceAndClaimApp.model.User;
import com.OJTProject.InsuranceAndClaimApp.repository.ClaimRepository;
import com.OJTProject.InsuranceAndClaimApp.repository.UserRepository;
import com.OJTProject.InsuranceAndClaimApp.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClaimServiceImpl implements ClaimService {
    @Autowired
    private ClaimRepository claimRepository;
//    private ClientRepository clientRepository;
    private UserRepository userRepository;



    public ClaimServiceImpl(ClaimRepository claimRepository, UserRepository userRepository) {
        this.claimRepository = claimRepository;
        this.userRepository = userRepository;
    }
    @Override
    public List<ClaimDto> findAllClaims() {
        List<Claim> claims = claimRepository.findAll();
        return claims.stream().map((claim) -> mapToClaimDto(claim)).collect(Collectors.toList());

    }

    @Override
    public Claim saveClaim(ClaimDto claimDto) {
        Claim claim = mapToClaim(claimDto);
        return claimRepository.save(claim);
    }

    @Override
    public void delete(Long claimId) {
        claimRepository.deleteById(claimId);
    }

    @Override
    public ClaimDto findClaimById(Long claimId) {
        Claim claim = claimRepository.findById(claimId).get();
        return mapToClaimDto(claim);
    }

    @Override
    public Claim updateClaim(ClaimDto claimDto) {
        Claim claim = mapToClaim(claimDto);
        return claimRepository.save(claim);

    }



    @Override
    public Claim saveUserClaim(ClaimDto claimDto, long userId) {

        User user = userRepository.findById(userId).get();
        Claim claim = mapToUserClaim(claimDto);
        claim.setUser(user);
        return claimRepository.save(claim);

    }

    @Override
    public List<ClaimDto> findAllUserClaims(long id) {
        List<Claim> claims = claimRepository.findByUserId(id);
        return claims.stream().map((claim) -> mapToClaimDto(claim)).collect(Collectors.toList());
    }

    @Override
    public long findAllUserClaims1(String email) {
        return claimRepository.countClaim(email);
    }



    private Claim mapToUserClaim(ClaimDto claim) {
        Claim claimDto = Claim.builder()
                .id(claim.getId())
                .claimer(claim.getClaimer())
                .client(claim.getClient())
                .admin(claim.getAdmin())
                .document(claim.getDocument())
                .claim_amount(claim.getClaim_amount())
                .status("For Verification")
                .createdOn(claim.getCreatedOn())
                .updatedOn(claim.getUpdatedOn())
                .build();
        return claimDto;
    }

    private Claim mapToClaim(ClaimDto claim) {
        Claim claimDto = Claim.builder()
                .id(claim.getId())
                .claimer(claim.getClaimer())
                .client(claim.getClient())
                .admin(claim.getAdmin())
                .document(claim.getDocument())
                .claim_amount(claim.getClaim_amount())
                .status(claim.getStatus())
                .createdOn(claim.getCreatedOn())
                .updatedOn(claim.getUpdatedOn())
                .build();
        return claimDto;
    }

    private ClaimDto mapToClaimDto(Claim claim) {
        ClaimDto claimDto = ClaimDto.builder()
                .id(claim.getId())
                .claimer(claim.getClaimer())
                .client(claim.getClient())
                .admin(claim.getAdmin())
                .document(claim.getDocument())
                .claim_amount(claim.getClaim_amount())
                .status(claim.getStatus())
                .createdOn(claim.getCreatedOn())
                .updatedOn(claim.getUpdatedOn())
                .build();
        return claimDto;
    }
}
