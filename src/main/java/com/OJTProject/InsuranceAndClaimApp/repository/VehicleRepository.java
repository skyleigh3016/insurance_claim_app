package com.OJTProject.InsuranceAndClaimApp.repository;


import com.OJTProject.InsuranceAndClaimApp.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
