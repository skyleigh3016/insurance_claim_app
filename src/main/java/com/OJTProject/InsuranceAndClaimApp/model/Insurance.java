package com.OJTProject.InsuranceAndClaimApp.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "insurances")
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String insurance_type;
    private String vehicle_type;
    private String vehicle_uses;
    private int age_coverage;
    private int tenure;
    private int annual_premium;
    private int monthly_premium;
    private int amount_insured;
    @CreationTimestamp
    private LocalDateTime createdOn;
    @UpdateTimestamp
    private LocalDateTime updatedOn;

//    @OneToMany(mappedBy = "insurances")
//    private Set<Client> clients = new HashSet<>();

    @OneToMany(mappedBy = "insurances", cascade = CascadeType.ALL)
    private Set<Client> clients = new HashSet<>();

}
