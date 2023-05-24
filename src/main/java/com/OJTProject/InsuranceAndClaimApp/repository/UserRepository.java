package com.OJTProject.InsuranceAndClaimApp.repository;

import com.OJTProject.InsuranceAndClaimApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    long count();

//    Object findByUsername(String un);
}
