package com.OJTProject.InsuranceAndClaimApp.repository;

// import java.util.Optional;

import com.OJTProject.InsuranceAndClaimApp.model.Claim;
import com.OJTProject.InsuranceAndClaimApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import com.OJTProject.InsuranceAndClaimApp.model.Insurance;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InsuranceRepository extends JpaRepository<Insurance, Long>{
    // Optional<Insurance> findByInsurance_type(String url);

    long count();
//    long countByInsurance_type(String type);
//@Query(value = "SELECT count(id) FROM Product where featured = :featured")
//public Long count(@Param("featured") boolean featured);
    @Query (value = "select count(i.insurance_type) from insurances i where i.insurance_type like '%Life%'")
    long countLife();

    @Query (value = "select count(i.insurance_type) from insurances i where i.insurance_type like '%Vehicle%'")
    long countVehicle();
//long countByInsurance_type(String insurance_type);

    @Query(value = "select i from insurances i where i.insurance_type like '%Life%'")
    List<Insurance> findByAddLineContains();

    @Query(value = "select i from insurances i where i.insurance_type like '%Vehicle%'")
    List<Insurance> findByAddLineContain();
}
