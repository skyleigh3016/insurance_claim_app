package com.OJTProject.InsuranceAndClaimApp.service.impl;

import com.OJTProject.InsuranceAndClaimApp.dto.ClientDto;
import com.OJTProject.InsuranceAndClaimApp.dto.ResponseDto;
import com.OJTProject.InsuranceAndClaimApp.dto.VehiclesDto;
import com.OJTProject.InsuranceAndClaimApp.model.Client;
import com.OJTProject.InsuranceAndClaimApp.model.User;
import com.OJTProject.InsuranceAndClaimApp.model.Vehicle;
import com.OJTProject.InsuranceAndClaimApp.repository.ClientRepository;
import com.OJTProject.InsuranceAndClaimApp.repository.UserRepository;
import com.OJTProject.InsuranceAndClaimApp.repository.VehicleRepository;
import com.OJTProject.InsuranceAndClaimApp.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {
    private ClientRepository clientRepository;
    private UserRepository userRepository;
    private VehicleRepository vehicleRepository;

    public ClientServiceImpl(ClientRepository clientRepository, UserRepository userRepository,VehicleRepository vehicleRepository) {
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public void createClient(Long userId, ClientDto clientDto) {
        User user = userRepository.findById(userId).get();
        Client client =mapToClient(clientDto);
        client.setUser(user);
        clientRepository.save(client);
    }


//    @Override
//    public List<ResponseDto> findAllClientInfo() {
//
//        return clientRepository.findByAddLineContains();
//    }

    @Override
    public List<ResponseDto> findAllClientInfo() {
        List<ResponseDto> responseDto = clientRepository.findByAddLineContains();
        return responseDto.stream().map((response) -> mapToInsuranceDto(response)).collect(Collectors.toList());

    }

    @Override
    public List<ResponseDto> findMyInsuranceInfo( String email) {
        List<ResponseDto> responseDto = clientRepository.findByAddLineContains1(email);
        return responseDto.stream().map((response) -> mapToInsuranceDto(response)).collect(Collectors.toList());

    }

    @Override
    public void createVehicles(Long userId, VehiclesDto vehiclesDto) {
        User user = userRepository.findById(userId).get();
        Vehicle vehicle =mapToVehicle(vehiclesDto);
        vehicle.setUser(user);
        vehicleRepository.save(vehicle);
    }

    private Vehicle mapToVehicle(VehiclesDto vehiclesDto) {
        return Vehicle.builder()
                .id(vehiclesDto.getId())
                .vehicle_type(vehiclesDto.getVehicle_type())
                .vehicle_model(vehiclesDto.getVehicle_model())
                .chassis_number(vehiclesDto.getChassis_number())
                .register_number(vehiclesDto.getRegister_number())
                .date_register(vehiclesDto.getDate_register())
                .createdOn(vehiclesDto.getCreatedOn())
                .updatedOn(vehiclesDto.getUpdatedOn())
                .build();
    }

    private ResponseDto mapToInsuranceDto(ResponseDto response) {
        ResponseDto responseDto = ResponseDto.builder()
                .insurance_type(response.getInsurance_type())
                .name(response.getName())
                .id(response.getId())
                .vehicle_type(response.getVehicle_type())
                .vehicle_uses(response.getVehicle_uses())
                .age_coverage(response.getAge_coverage())
                .tenure(response.getTenure())
                .monthly_premium(response.getMonthly_premium())
                .annual_premium(response.getAnnual_premium())
                .amount_insured(response.getAmount_insured())
//                .gender(response.getGender())
//                .street(response.getStreet())
//                .barangay(response.getBarangay())
//                .municipality(response.getMunicipality())
//                .state(response.getState())
                .build();
        return responseDto;
    }


    private Client mapToClient(ClientDto clientDto){
        return Client.builder()
                .id(clientDto.getId())
                .insurance_type(clientDto.getInsurance_type())
                .insurances(clientDto.getInsurances())
                .createdOn(clientDto.getCreatedOn())
                .updatedOn(clientDto.getUpdatedOn())
                .build();
    }
}
