package com.OJTProject.InsuranceAndClaimApp.repository;

import com.OJTProject.InsuranceAndClaimApp.model.PdfFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PdfFileRepository extends JpaRepository<PdfFile, Long> {
}
