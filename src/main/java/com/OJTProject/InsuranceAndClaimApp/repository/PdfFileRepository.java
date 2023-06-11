package com.OJTProject.InsuranceAndClaimApp.repository;

import com.OJTProject.InsuranceAndClaimApp.model.Claim;
import com.OJTProject.InsuranceAndClaimApp.model.PdfFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PdfFileRepository extends JpaRepository<PdfFile, Long> {
    @Query(value = "select sum(c.claim_amount ) from pdf_claims c",nativeQuery = true)
    int totalClaimed ();
    @Query(value = "select sum(c.claim_amount ) from pdf_claims c  WHERE c.user.email LIKE CONCAT('%', :email, '%')")
    int totalMyClaimed (String email);
    List<PdfFile> findByUserId(long id);
}
