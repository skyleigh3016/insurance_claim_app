package com.OJTProject.InsuranceAndClaimApp.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Entity(name = "clients")
public class Client {
    public Insurance getInsurances() {
        return insurances;
    }

    public void setInsurances(Insurance insurances) {
        this.insurances = insurances;
    }
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String insurance_type;
    @ManyToOne
   @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @CreationTimestamp
    private LocalDateTime createdOn;
    @UpdateTimestamp
    private LocalDateTime updatedOn;

    @ManyToOne
    @JoinColumn(name="insurance_id",  nullable = false)
    private Insurance insurances;
}
