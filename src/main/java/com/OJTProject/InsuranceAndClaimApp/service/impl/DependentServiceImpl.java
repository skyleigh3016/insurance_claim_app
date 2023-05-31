package com.OJTProject.InsuranceAndClaimApp.service.impl;

import com.OJTProject.InsuranceAndClaimApp.dto.BeneficiaryDto;
import com.OJTProject.InsuranceAndClaimApp.dto.DependentDto;
import com.OJTProject.InsuranceAndClaimApp.model.Beneficiary;
import com.OJTProject.InsuranceAndClaimApp.model.Dependent;
import com.OJTProject.InsuranceAndClaimApp.model.User;
import com.OJTProject.InsuranceAndClaimApp.repository.BeneficiaryRepository;
import com.OJTProject.InsuranceAndClaimApp.repository.DependentRepository;
import com.OJTProject.InsuranceAndClaimApp.repository.UserRepository;
import com.OJTProject.InsuranceAndClaimApp.service.BeneficiaryService;
import com.OJTProject.InsuranceAndClaimApp.service.DependentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DependentServiceImpl implements DependentService {

    private DependentRepository dependentRepository;
    private UserRepository userRepository;
    public DependentServiceImpl(DependentRepository dependentRepository, UserRepository userRepository) {
        this.dependentRepository = dependentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void insertDependent(Long userId, String dependent, String contact3, int age3, String address3, String dependent1, String contact4, int age4, String address4, String dependent2, String contact5, int age5, String address5) {
        User user = userRepository.findById(userId).get();
        Dependent dependent3 = new Dependent();
        dependent3.setDependent(dependent);
        dependent3.setAge(age3);
        dependent3.setContact(contact3);
        dependent3.setAddress(address3);
        dependent3.setUser(user);

        Dependent dependent4 = new Dependent();
        dependent4.setDependent(dependent1);
        dependent4.setAge(age4);
        dependent4.setContact(contact4);
        dependent4.setAddress(address4);
        dependent4.setUser(user);

        Dependent dependent5 = new Dependent();
        dependent5.setDependent(dependent2);
        dependent5.setAge(age5);
        dependent5.setContact(contact5);
        dependent5.setAddress(address5);
        dependent5.setUser(user);

        dependentRepository.saveAll(List.of(dependent3,dependent4,dependent5));
    }

    @Override
    public List<DependentDto> findUserInsurance(Long userId) {
        List<Dependent> dependents = dependentRepository.findByAddLineContains(userId);
        return dependents.stream().map((dependent) -> mapToDependentDto(dependent)).collect(Collectors.toList());

    }

    private DependentDto mapToDependentDto(Dependent dependent) {
        DependentDto dependentDto = DependentDto.builder()
                .id(dependent.getId())
                .dependent(dependent.getDependent())
                .age(dependent.getAge())
                .contact(dependent.getContact())
                .address(dependent.getAddress())
                .createdOn(dependent.getCreatedOn())
                .updatedOn(dependent.getUpdatedOn())
                .build();
        return dependentDto;
    }
}
