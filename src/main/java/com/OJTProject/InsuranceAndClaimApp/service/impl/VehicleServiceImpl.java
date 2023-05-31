package com.OJTProject.InsuranceAndClaimApp.service.impl;

import com.OJTProject.InsuranceAndClaimApp.dto.DependentDto;
import com.OJTProject.InsuranceAndClaimApp.dto.VehiclesDto;
import com.OJTProject.InsuranceAndClaimApp.model.Dependent;
import com.OJTProject.InsuranceAndClaimApp.model.Vehicle;
import com.OJTProject.InsuranceAndClaimApp.repository.DependentRepository;
import com.OJTProject.InsuranceAndClaimApp.repository.UserRepository;
import com.OJTProject.InsuranceAndClaimApp.repository.VehicleRepository;
import com.OJTProject.InsuranceAndClaimApp.service.VehicleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {
    private VehicleRepository vehicleRepository;
    private UserRepository userRepository;
    public VehicleServiceImpl(VehicleRepository vehicleRepository, UserRepository userRepository) {
        this.vehicleRepository = vehicleRepository;
        this.userRepository = userRepository;
    }
    @Override
    public List<VehiclesDto> findUserVehicle(Long userId) {
        List<Vehicle> vehicles =vehicleRepository .findByAddLineContains(userId);
        return vehicles.stream().map((vehicle) -> mapToVehicleDto(vehicle)).collect(Collectors.toList());

    }

    private VehiclesDto mapToVehicleDto(Vehicle vehicle) {
        VehiclesDto vehiclesDto = VehiclesDto.builder()
                .id(vehicle.getId())
                .vehicle_type(vehicle.getVehicle_type())
                .vehicle_model(vehicle.getVehicle_model())
                .register_number(vehicle.getRegister_number())
                .chassis_number(vehicle.getChassis_number())
                .date_register(vehicle.getDate_register())
                .createdOn(vehicle.getCreatedOn())
                .updatedOn(vehicle.getUpdatedOn())
                .build();
        return vehiclesDto;
    }
}
