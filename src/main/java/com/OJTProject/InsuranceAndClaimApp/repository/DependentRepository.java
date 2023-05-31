package com.OJTProject.InsuranceAndClaimApp.repository;


import com.OJTProject.InsuranceAndClaimApp.model.Beneficiary;
import com.OJTProject.InsuranceAndClaimApp.model.Dependent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DependentRepository extends JpaRepository<Dependent, Long> {

    @Query(value = "select d from dependent d WHERE d.user.id = :userId")
    List<Dependent> findByAddLineContains(Long userId);
}
