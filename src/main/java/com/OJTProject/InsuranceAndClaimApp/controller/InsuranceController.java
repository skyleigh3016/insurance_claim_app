package com.OJTProject.InsuranceAndClaimApp.controller;

import java.util.List;

import com.OJTProject.InsuranceAndClaimApp.config.CustomUserDetails;
import com.OJTProject.InsuranceAndClaimApp.dto.VehicleDto;
import com.OJTProject.InsuranceAndClaimApp.model.Insurance;

import com.OJTProject.InsuranceAndClaimApp.model.User;
import com.OJTProject.InsuranceAndClaimApp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.OJTProject.InsuranceAndClaimApp.dto.InsuranceDto;
import com.OJTProject.InsuranceAndClaimApp.service.InsuranceService;

// import ch.qos.logback.core.model.Model;
import org.springframework.ui.Model;

//import javax.validation.Valid;

@Controller
public class InsuranceController {
    private InsuranceService  insuranceService;

    private UserService userService;
    public InsuranceController(UserService userService,InsuranceService insuranceService){
        this.insuranceService = insuranceService;
        this.userService = userService;
    }

    @GetMapping("/Insurance")
    public String listInsurances(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model){
        String email = loggedUser.getUsername();
        User user = userService.getByEmail(email);
        List<InsuranceDto> insurances = insuranceService.findAllInsurance();
        model.addAttribute("insurances", insurances);
        model.addAttribute("user",user);
        return "admin/insurance/list-insurance";
    }

    @GetMapping("/user-insurance")
    public String listUserInsurances(@AuthenticationPrincipal CustomUserDetails loggedUser,Model model){
        String email = loggedUser.getUsername();
        User user = userService.getByEmail(email);
        List<InsuranceDto> insurances = insuranceService.findAllInsurance();
        model.addAttribute("insurances", insurances);
        model.addAttribute("user",user);
        return "user/lifeOffer/life-offer";
    }
    @GetMapping("/user-vehicle")
    public String listUserVehicle(@AuthenticationPrincipal CustomUserDetails loggedUser,Model model){
        String email = loggedUser.getUsername();
        User user = userService.getByEmail(email);
        List<InsuranceDto> insurances = insuranceService.findAllVehicle();
        model.addAttribute("insurances", insurances);
        model.addAttribute("user",user);
        return "user/vehicleOffer/vehicle-offer";
    }

    @GetMapping("/vehicle")
    public String listVehicle(@AuthenticationPrincipal CustomUserDetails loggedUser,Model model){
        String email = loggedUser.getUsername();
        User user = userService.getByEmail(email);
        List<InsuranceDto> insurances = insuranceService.findAllVehicle();
        model.addAttribute("insurances", insurances);
        model.addAttribute("user",user);
        return "admin/insurance/list-vehicle";
    }

    @GetMapping("/insurance/new")
    public String createInsuranceForm(@AuthenticationPrincipal CustomUserDetails loggedUser,Model model) {
        String email = loggedUser.getUsername();
        User user = userService.getByEmail(email);
        Insurance insurance = new Insurance();
        model.addAttribute("insurance", insurance);
        model.addAttribute("user",user);
        return "admin/insurance/insurance-create";
    }

    @GetMapping("/vehicle/new")
    public String createVehicleForm(@AuthenticationPrincipal CustomUserDetails loggedUser,Model model) {
        String email = loggedUser.getUsername();
        User user = userService.getByEmail(email);
        Insurance insurance = new Insurance();
        model.addAttribute("insurance", insurance);
        model.addAttribute("user",user);
        return "admin/insurance/vehicle-create";
    }

    @PostMapping("/insurance/new")
    public String saveInsurance(@Valid @ModelAttribute("insurance") InsuranceDto insuranceDto, BindingResult result,
                                @RequestParam("insurance_type") String insurance_type,
                                @AuthenticationPrincipal CustomUserDetails loggedUser, Model model){


//        if(insurance_type.equals("Vehicle_Insurance") && result1.hasErrors()) {
//            String email = loggedUser.getUsername();
//            User user = userService.getByEmail(email);
//            model.addAttribute("insurance", vehicleDto);
//            model.addAttribute("user",user);
//            return "admin/insurance/insurance-create";
//        }

        if(insurance_type.equals("Vehicle_Insurance")) {
            insuranceService.saveInsurance(insuranceDto);
            return "redirect:/vehicle";
        }

        if(result.hasErrors()) {
            String email = loggedUser.getUsername();
            User user = userService.getByEmail(email);
            model.addAttribute("insurance", insuranceDto);
            model.addAttribute("user",user);
            return "admin/insurance/insurance-create";

        }




        insuranceService.saveInsurance(insuranceDto);
        return "redirect:/Insurance";
    }

    @GetMapping("/insurance/{insuranceId}/edit")
    public String editInsuranceForm(@PathVariable("insuranceId") Long insuranceId,
                                    @AuthenticationPrincipal CustomUserDetails loggedUser,Model model) {
        String email = loggedUser.getUsername();
        User user = userService.getByEmail(email);
        InsuranceDto insurance = insuranceService.findInsuranceById(insuranceId);
        model.addAttribute("insurance", insurance);
        model.addAttribute("user",user);
        return "admin/insurance/insurance-edit";
    }

    @GetMapping("/vehicle/{vehicleId}/edit")
    public String editVehicleForm(@PathVariable("vehicleId") Long vehicleId,
                                  @AuthenticationPrincipal CustomUserDetails loggedUser,Model model) {
        String email = loggedUser.getUsername();
        User user = userService.getByEmail(email);
        InsuranceDto insurance = insuranceService.findInsuranceById(vehicleId);
        model.addAttribute("insurance", insurance);
        model.addAttribute("user",user);
        return "admin/insurance/vehicle-edit";
    }

    @PostMapping("/insurance/{insuranceId}/edit")
        public String updateInsurance(@PathVariable("insuranceId") Long insuranceId,
                                      @Valid @ModelAttribute("insurance") InsuranceDto insurance,BindingResult result,
                                      @AuthenticationPrincipal CustomUserDetails loggedUser,Model model) {
        if(result.hasErrors()) {
            model.addAttribute("insurance", insurance);
            return "admin/insurance/insurance-edit";
        }
        insurance.setId(insuranceId);
            insuranceService.updateInsurance(insurance);
        return "redirect:/Insurance";
    }
    @PostMapping("/vehicle/{vehicleId}/edit")
    public String updateVehicle(@PathVariable("vehicleId") Long vehicleId, @ModelAttribute("insurance") InsuranceDto insuranceDto,
    @AuthenticationPrincipal CustomUserDetails loggedUser) {


        insuranceDto.setId(vehicleId);
        insuranceService.updateVehicle(insuranceDto);
        return "redirect:/vehicle";
    }


    @GetMapping("/insurances/{insuranceId}/delete")
    public String deleteInsurance(@PathVariable("insuranceId")Long insuranceId) {
        insuranceService.delete(insuranceId);
        return "redirect:/Insurance";
    }
}
