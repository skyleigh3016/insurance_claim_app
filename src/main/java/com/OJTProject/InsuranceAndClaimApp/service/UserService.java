package com.OJTProject.InsuranceAndClaimApp.service;



import com.OJTProject.InsuranceAndClaimApp.dto.UserDto;
import com.OJTProject.InsuranceAndClaimApp.model.Claim;
import com.OJTProject.InsuranceAndClaimApp.model.User;

import java.util.List;
public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();

    void saveUser1(UserDto userDto);

    User findUserByEmail1(String email);

    List<UserDto> findAllUsers1();

    void delete(Long userId);

    User getByEmail(String email);

    User findUserById(Long userId);

    long countUsers();

     long countLife();

    long totalAmount();

    long countVehicle();

    long totalMyAmount(String email);




//    Object findByUsername(String un);
}
