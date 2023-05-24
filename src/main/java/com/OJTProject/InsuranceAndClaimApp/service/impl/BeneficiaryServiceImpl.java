package com.OJTProject.InsuranceAndClaimApp.service.impl;

import com.OJTProject.InsuranceAndClaimApp.dto.ClientDto;
import com.OJTProject.InsuranceAndClaimApp.dto.VehiclesDto;
import com.OJTProject.InsuranceAndClaimApp.model.Beneficiary;
import com.OJTProject.InsuranceAndClaimApp.model.Client;
import com.OJTProject.InsuranceAndClaimApp.model.User;
import com.OJTProject.InsuranceAndClaimApp.model.Vehicle;
import com.OJTProject.InsuranceAndClaimApp.repository.BeneficiaryRepository;
import com.OJTProject.InsuranceAndClaimApp.repository.ClientRepository;
import com.OJTProject.InsuranceAndClaimApp.repository.UserRepository;
import com.OJTProject.InsuranceAndClaimApp.repository.VehicleRepository;
import com.OJTProject.InsuranceAndClaimApp.service.BeneficiaryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {
    private BeneficiaryRepository beneficiaryRepository;
    private UserRepository userRepository;
    public BeneficiaryServiceImpl(BeneficiaryRepository beneficiaryRepository, UserRepository userRepository) {
        this.beneficiaryRepository = beneficiaryRepository;
        this.userRepository = userRepository;
    }


//    @Override
    public void insertBeneficiary(Long userId) {
        User user = userRepository.findById(userId).get();
        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setBeneficiary("Eugenia Andal");
        beneficiary.setAge(39);
        beneficiary.setContact("091-545-568-89");
        beneficiary.setAddress("Tranca Bay Laguna");
        beneficiary.setUser(user);

        Beneficiary beneficiary1 = new Beneficiary();
        beneficiary1.setBeneficiary("Sergio Andal");
        beneficiary1.setAge(33);
        beneficiary1.setContact("091-545-568-89");
        beneficiary1.setAddress("Sta Cruz Bay Laguna");
        beneficiary1.setUser(user);

        beneficiaryRepository.saveAll(List.of(beneficiary,beneficiary1));
    }

    @Override
    public void insertBeneficiary1(Long userId, String beneficiary, String contact, int age, String address, String beneficiary1, String contact1, int age1, String address1, String beneficiary2, String contact2, int age2, String address2) {
        User user = userRepository.findById(userId).get();
        Beneficiary beneficiary4 = new Beneficiary();
        beneficiary4.setBeneficiary(beneficiary);
        beneficiary4.setAge(age);
        beneficiary4.setContact(contact);
        beneficiary4.setAddress(address);
        beneficiary4.setUser(user);

        Beneficiary beneficiary3 = new Beneficiary();
        beneficiary3.setBeneficiary(beneficiary1);
        beneficiary3.setAge(age1);
        beneficiary3.setContact(contact1);
        beneficiary3.setAddress(address1);
        beneficiary3.setUser(user);

        Beneficiary beneficiary5 = new Beneficiary();
        beneficiary5.setBeneficiary(beneficiary2);
        beneficiary5.setAge(age2);
        beneficiary5.setContact(contact2);
        beneficiary5.setAddress(address2);
        beneficiary5.setUser(user);

        beneficiaryRepository.saveAll(List.of(beneficiary4,beneficiary3,beneficiary5));
    }
}
