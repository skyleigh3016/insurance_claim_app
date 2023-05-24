package com.OJTProject.InsuranceAndClaimApp.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String gender;
//    @NotEmpty
    @Column(length = 64)
    private String avatar;
@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    @Min(value = 18, message = "minimum Age is 18")
    private Integer age;
    private String street;
    private String barangay;
    private String municipality;
    private String state;
    private String country;
    private Integer house_no;
    private Integer postal_code;
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;
    @NotEmpty(message = "Password should not be empty")
    private String password;
}
