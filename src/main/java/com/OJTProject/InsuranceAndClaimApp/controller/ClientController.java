package com.OJTProject.InsuranceAndClaimApp.controller;


import com.OJTProject.InsuranceAndClaimApp.config.CustomUserDetails;
import com.OJTProject.InsuranceAndClaimApp.dto.*;
import com.OJTProject.InsuranceAndClaimApp.model.Beneficiary;
import com.OJTProject.InsuranceAndClaimApp.model.Client;
import com.OJTProject.InsuranceAndClaimApp.model.Insurance;
import com.OJTProject.InsuranceAndClaimApp.model.User;
import com.OJTProject.InsuranceAndClaimApp.service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

//import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@Controller
public class ClientController {
    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private ClientService clientService;
    @Autowired
    private UserService userService;
    @Autowired
    private BeneficiaryService beneficiaryService;
    @Autowired
    private DependentService dependentService;

    @Autowired
    private VehicleService vehicleService;



    @GetMapping("/clients/{userId}/new")
    public String createClientForm(@PathVariable("userId")Long userId, Model model){

        Client client = new Client();
        Insurance insurance = new Insurance();
        User user = userService.findUserById(userId);

        model.addAttribute("allVehicles", insuranceService.findAllVehicle());
        model.addAttribute("allClients", insuranceService.findAllInsurance());
        model.addAttribute("userId",userId);
        model.addAttribute("client",client);
        model.addAttribute("insurance",insurance);
        model.addAttribute("user",user);
        return "admin/client/client-create";

    }

    @PostMapping("/clients/{userId}")
    public String createEvent(@PathVariable("userId") Long userId,
                              @RequestParam("beneficiary") String beneficiary,
                              @RequestParam("age") int age,
                              @RequestParam("contact") String contact,
                              @RequestParam("address") String address,
                              @RequestParam("beneficiary1") String beneficiary1,
                              @RequestParam("age1") int age1,
                              @RequestParam("contact1") String contact1,
                              @RequestParam("address1") String address1,
                              @RequestParam("beneficiary2") String beneficiary2,
                              @RequestParam("age2") int age2,
                              @RequestParam("contact2") String contact2,
                              @RequestParam("address2") String address2,
                              @RequestParam("dependent") String dependent,
                              @RequestParam("age3") int age3,
                              @RequestParam("contact3") String contact3,
                              @RequestParam("address3") String address3,
                              @RequestParam("dependent1") String dependent1,
                              @RequestParam("age4") int age4,
                              @RequestParam("contact4") String contact4,
                              @RequestParam("address4") String address4,
                              @RequestParam("dependent2") String dependent2,
                              @RequestParam("age5") int age5,
                              @RequestParam("contact5") String contact5,
                              @RequestParam("address5") String address5,
//                              @RequestParam("insurance_type") String insurance_type,
                              @ModelAttribute("event") ClientDto clientDto,
                              @ModelAttribute("vehicle") VehiclesDto vehiclesDto,
                              @ModelAttribute("insurance") Insurance insurances,Model model) {
//        if(result.hasErrors()) {
//            model.addAttribute("client", clientDto);
//            return "admin/client/client-create";
//        }
        if (age >= 100){
            clientDto.setInsurances(insurances);
            clientService.createClient(userId, clientDto);
            clientService.createVehicles(userId, vehiclesDto);
//            beneficiaryService.insertBeneficiary1(userId,beneficiary,contact,age,address,beneficiary1,contact1,age1,address1,beneficiary2,contact2,age2,address2);
//            dependentService.insertDependent(userId,dependent,contact3,age3,address3,dependent1,contact4,age4,address4,dependent2,contact5,age5,address5);
            return "redirect:/users";
        }

        if (age >= 18 && age1 >= 18 && age2 >= 18 && age3 >= 18 && age4 >= 18 && age5 >= 18){
            clientDto.setInsurances(insurances);
            clientService.createClient(userId, clientDto);
//            clientService.createVehicles(userId, vehiclesDto);
            beneficiaryService.insertBeneficiary1(userId,beneficiary,contact,age,address,beneficiary1,contact1,age1,address1,beneficiary2,contact2,age2,address2);
            dependentService.insertDependent(userId,dependent,contact3,age3,address3,dependent1,contact4,age4,address4,dependent2,contact5,age5,address5);
            return "redirect:/users?success";


        }else {
//            clientDto.setInsurances(insurances);
//            clientService.createClient(userId, clientDto);
//            clientService.createVehicles(userId, vehiclesDto);
//            beneficiaryService.insertBeneficiary1(userId,beneficiary,contact,age,address,beneficiary1,contact1,age1,address1,beneficiary2,contact2,age2,address2);
//            return "redirect:/users";
            return "redirect:/clients/{userId}/new?success";
        }
//        clientDto.setInsurances(insurances);
//        clientService.createClient(userId, clientDto);
//        clientService.createVehicles(userId, vehiclesDto);
//        beneficiaryService.insertBeneficiary1(userId,beneficiary,contact,age,address,beneficiary1,contact1,age1,address1,beneficiary2,contact2,age2,address2);
//        return "redirect:/users";

    }


    @GetMapping("/clients_info")
    public String listClientInfo(@AuthenticationPrincipal CustomUserDetails loggedUser,Model model){
        String email = loggedUser.getUsername();
        User user = userService.getByEmail(email);
        List<ResponseDto> clients = clientService.findAllClientInfo();
        model.addAttribute("user", user);
        model.addAttribute("clients", clients);
        return "admin/client/list-client";
    }

    @GetMapping("/client/{clientId}/view")
    public String viewClient(@PathVariable("clientId")Long userId,
                             @AuthenticationPrincipal CustomUserDetails loggedUser,Model model){
        String email = loggedUser.getUsername();
        User user1 = userService.getByEmail(email);
        User user = userService.findUserById(userId);
        List<BeneficiaryDto> beneficiary = beneficiaryService.findUserInsurance1(userId);
        List<DependentDto> dependent = dependentService.findUserInsurance(userId);
        List<VehiclesDto> vehicle = vehicleService.findUserVehicle(userId);
        model.addAttribute("beneficiary1",beneficiary);
        model.addAttribute("dependent",dependent);
        model.addAttribute("vehicle",vehicle);
        model.addAttribute("user",user);
        model.addAttribute("user1",user1);
        return "admin/client/view-client";
    }

    @GetMapping("/my-insurance")
    public String myInsuranceInfo(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model){
        String email = loggedUser.getUsername();
        User user = userService.getByEmail(email);
        List<ResponseDto> clients = clientService.findMyInsuranceInfo(email);
        model.addAttribute("clients", clients);
        model.addAttribute("user",user);
        return "user/myInsurance/my-insurance";
    }





}
