package com.OJTProject.InsuranceAndClaimApp.repository;

import com.OJTProject.InsuranceAndClaimApp.dto.ResponseDto;
import com.OJTProject.InsuranceAndClaimApp.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
//    @Query(value = "select c.insurance_type, u.name, i.vehicle_type from clients c INNER JOIN insurances i  INNER JOIN User u  where c.insurance_type like '%Life%'")
    @Query(value = "select new com.OJTProject.InsuranceAndClaimApp.dto.ResponseDto(c.insurance_type, u.name,u.id, i.vehicle_type, i.vehicle_uses, i.age_coverage,i.tenure,i.monthly_premium,i.annual_premium,i.amount_insured) from clients c INNER JOIN insurances i  ON i.id = c.insurances.id  INNER JOIN User u ON u.id  = c.user.id")
    List<ResponseDto> findByAddLineContains();

    @Query(value = "select new com.OJTProject.InsuranceAndClaimApp.dto.ResponseDto(c.insurance_type, u.name,u.id, i.vehicle_type, i.vehicle_uses, i.age_coverage,i.tenure,i.monthly_premium,i.annual_premium,i.amount_insured) from clients c INNER JOIN insurances i  ON i.id = c.insurances.id  INNER JOIN User u ON u.id  = c.user.id WHERE u.email LIKE CONCAT('%', :email, '%')")
    List<ResponseDto> findByAddLineContains1(String email);
}
