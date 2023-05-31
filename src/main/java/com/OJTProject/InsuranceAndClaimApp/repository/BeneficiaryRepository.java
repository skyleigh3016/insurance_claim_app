package com.OJTProject.InsuranceAndClaimApp.repository;


import com.OJTProject.InsuranceAndClaimApp.model.Beneficiary;
import com.OJTProject.InsuranceAndClaimApp.model.Claim;
import com.OJTProject.InsuranceAndClaimApp.model.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {
    List<Beneficiary> findByUserId(long id);

    @Query(value = "select b from beneficiary b WHERE b.user.id = :userId")
    List<Beneficiary> findByAddLineContains(Long userId);
}
