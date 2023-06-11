package com.OJTProject.InsuranceAndClaimApp.service.impl;

import com.OJTProject.InsuranceAndClaimApp.dto.ClaimDto;
import com.OJTProject.InsuranceAndClaimApp.dto.UserDto;
import com.OJTProject.InsuranceAndClaimApp.model.Claim;
import com.OJTProject.InsuranceAndClaimApp.model.PdfFile;
import com.OJTProject.InsuranceAndClaimApp.model.Role;
import com.OJTProject.InsuranceAndClaimApp.model.User;
import com.OJTProject.InsuranceAndClaimApp.repository.*;
import com.OJTProject.InsuranceAndClaimApp.service.UserService;
//import net.javaguides.springboot.dto.UserDto;
//import net.javaguides.springboot.entity.Role;
//import net.javaguides.springboot.entity.User;
//import net.javaguides.springboot.repository.RoleRepository;
//import net.javaguides.springboot.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private InsuranceRepository insuranceRepository;
    private ClaimRepository claimRepository;
    private PdfFileRepository pdfFileRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           InsuranceRepository insuranceRepository,
                           ClaimRepository claimRepository,
                           PdfFileRepository pdfFileRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.insuranceRepository = insuranceRepository;
        this.claimRepository = claimRepository;
        this.pdfFileRepository = pdfFileRepository;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setAge(userDto.getAge());
        user.setAvatar(userDto.getAvatar());
        user.setGender(userDto.getGender());
        user.setBirthday(userDto.getBirthday());
        user.setEmail(userDto.getEmail());
        user.setHouse_no(userDto.getHouse_no());
        user.setStreet(userDto.getStreet());
        user.setBarangay(userDto.getBarangay());
        user.setMunicipality(userDto.getMunicipality());
        user.setState(userDto.getState());
        user.setCountry(userDto.getCountry());
        user.setPostal_code(userDto.getPostal_code());
        // encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));


        Role role = roleRepository.findByName("ROLE_ADMIN");
        if(role == null){
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public void saveUser1(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setAge(userDto.getAge());
        user.setAvatar(userDto.getAvatar());
        user.setGender(userDto.getGender());
        user.setBirthday(userDto.getBirthday());
        user.setEmail(userDto.getEmail());
        user.setHouse_no(userDto.getHouse_no());
        user.setStreet(userDto.getStreet());
        user.setBarangay(userDto.getBarangay());
        user.setMunicipality(userDto.getMunicipality());
        user.setState(userDto.getState());
        user.setCountry(userDto.getCountry());
        user.setPostal_code(userDto.getPostal_code());
        // encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));


        Role role = roleRepository.findByName("ROLE_USER");
        if(role == null){
            role = checkRoleExist1();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserByEmail1(String email) {
        return userRepository.findByEmail(email);
    }
    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> findAllUsers1() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map((user) -> mapToUserDto2(user))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserById(Long userId) {
        User user = userRepository.findById(userId).get();
        return mapToUserDto1(user);
    }

    @Override
    public long countUsers() {
        long count = userRepository.count();
        return count;
    }

    @Override
    public long countLife() {
        long count = insuranceRepository.countLife();
//        long count = repo.countByDept("IT");
        return count;
    }

    @Override
    public long totalAmount() {
        long total = claimRepository.totalClaimed();
//        long count = repo.countByDept("IT");
        return total;
    }

    @Override
    public long countVehicle() {
        long count = insuranceRepository.countVehicle();
//        long count = repo.countByDept("IT");
        return count;
    }

    @Override
    public long totalMyAmount(String email) {
        long total = claimRepository.totalMyClaimed(email);
//        long count = repo.countByDept("IT");
        return total;
    }

    @Override
    public List<PdfFile> findAllUserPdfClaims(long id) {
        List<PdfFile> pdfFiles = pdfFileRepository.findByUserId(id);
        return pdfFiles.stream().map((claim) -> mapToClaimDto(claim)).collect(Collectors.toList());
    }

    private PdfFile mapToClaimDto(PdfFile claim) {
        PdfFile claimDto = PdfFile.builder()
                .id(claim.getId())
                .claimer(claim.getClaimer())
                .client(claim.getClient())
                .fileName(claim.getFileName())
                .claim_amount(claim.getClaim_amount())
                .status(claim.getStatus())
                .user(claim.getUser())
                .createdOn(claim.getCreatedOn())
                .updatedOn(claim.getUpdatedOn())
                .build();
        return claimDto;
    }


    private User mapToUserDto1(User user) {
        User userDto = new User();
        userDto.setName(user.getName());
        userDto.setAge(user.getAge());
        userDto.setBirthday(user.getBirthday());
        userDto.setGender(user.getGender());
        userDto.setAvatar(user.getPhotosImagePath());
        userDto.setEmail(user.getEmail());
        userDto.setBarangay(user.getBarangay());
        userDto.setMunicipality(user.getMunicipality());
        userDto.setState(user.getState());
        return userDto;
    }


//    @Override
//    public Object findByUsername(String un) {
//        return userRepository.findByUsername(un);
//    }

    private UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto();
        String[] str = user.getName().split(" ");
        userDto.setFirstName(str[0]);
        userDto.setLastName(str[1]);
        userDto.setId(user.getId());
        userDto.setAge(user.getAge());
        userDto.setBirthday(user.getBirthday());
        userDto.setGender(user.getGender());
        userDto.setHouse_no(user.getHouse_no());
        userDto.setStreet(user.getStreet());
        userDto.setBarangay(user.getBarangay());
        userDto.setMunicipality(user.getMunicipality());
        userDto.setState(user.getState());
        userDto.setCountry(user.getCountry());
        userDto.setPostal_code(user.getPostal_code());
        userDto.setAvatar(user.getPhotosImagePath());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private UserDto mapToUserDto2(User user){
        UserDto userDto = new UserDto();
        String[] str = user.getName().split(" ");
        userDto.setFirstName(str[0]);
        userDto.setLastName(str[1]);
        userDto.setId(user.getId());
        userDto.setAge(user.getAge());
        userDto.setBirthday(user.getBirthday());
        userDto.setGender(user.getGender());
        userDto.setHouse_no(user.getHouse_no());
        userDto.setStreet(user.getStreet());
        userDto.setBarangay(user.getBarangay());
        userDto.setMunicipality(user.getMunicipality());
        userDto.setState(user.getState());
        userDto.setCountry(user.getCountry());
        userDto.setPostal_code(user.getPostal_code());
        userDto.setAvatar(user.getPhotosImagePath());
        userDto.setEmail(user.getEmail());
        return userDto;
    }


    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

    private Role checkRoleExist1(){
        Role role = new Role();
        role.setName("ROLE_USER");
        return roleRepository.save(role);
    }
}
