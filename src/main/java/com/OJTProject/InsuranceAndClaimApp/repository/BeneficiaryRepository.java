package com.OJTProject.InsuranceAndClaimApp.repository;


import com.OJTProject.InsuranceAndClaimApp.model.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {
}
