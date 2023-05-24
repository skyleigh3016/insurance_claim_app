package com.OJTProject.InsuranceAndClaimApp.repository;

import com.OJTProject.InsuranceAndClaimApp.dto.ResponseUserClaimDto;
import com.OJTProject.InsuranceAndClaimApp.model.Claim;
import com.OJTProject.InsuranceAndClaimApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClaimRepository extends JpaRepository<Claim, Long> {
//    Optional<Claim> findClaimBy(String url);
    Optional<Claim> findByClaimer(String url);
//
//    @Query(value = "select new com.OJTProject.InsuranceAndClaimApp.dto.ResponseUserClaimDto(u.name, c.claimer, c.client, c.admin, c.claim_amount, c.status,c.document) from claims c  JOIN User u ON u.id  = c.user.id WHERE u.email LIKE CONCAT('%', :email, '%')")
//    List<ResponseUserClaimDto> findByAddLineContains1(String email);
//@Query(value = "SELECT SUM(claim_amount) FROM claims", nativeQuery = true)
@Query(value = "select sum(c.claim_amount ) from claims c",nativeQuery = true)
int totalClaimed ();
    @Query(value = "select sum(c.claim_amount ) from claims c  WHERE c.user.email LIKE CONCAT('%', :email, '%')")
    int totalMyClaimed (String email);
    @Query (value = "select count(c.user.id) from claims c where c.user.email LIKE CONCAT('%', :email, '%')")
    long countClaim(String email);
    List<Claim> findByUserId(long id);

    Claim findByUserEmail(String email);


}
