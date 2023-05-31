package com.OJTProject.InsuranceAndClaimApp.repository;


import com.OJTProject.InsuranceAndClaimApp.model.Dependent;
import com.OJTProject.InsuranceAndClaimApp.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query(value = "select v from vehicles v WHERE v.user.id = :userId")
    List<Vehicle> findByAddLineContains(Long userId);

}
