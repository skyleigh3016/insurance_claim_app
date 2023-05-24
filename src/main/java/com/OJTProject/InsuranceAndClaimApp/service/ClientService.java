package com.OJTProject.InsuranceAndClaimApp.service;

import com.OJTProject.InsuranceAndClaimApp.dto.ClientDto;
import com.OJTProject.InsuranceAndClaimApp.dto.ResponseDto;
import com.OJTProject.InsuranceAndClaimApp.dto.VehiclesDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {
    void createClient(Long userId, ClientDto clientDto);

    public List<ResponseDto> findAllClientInfo();

    List<ResponseDto> findMyInsuranceInfo( String email);

    void createVehicles(Long userId, VehiclesDto vehiclesDto);
}
