package com.OJTProject.InsuranceAndClaimApp.controller;

import java.util.List;

import com.OJTProject.InsuranceAndClaimApp.model.Insurance;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import com.OJTProject.InsuranceAndClaimApp.dto.InsuranceDto;
import com.OJTProject.InsuranceAndClaimApp.service.InsuranceService;

// import ch.qos.logback.core.model.Model;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

//import javax.validation.Valid;

@Controller
public class InsuranceController {
    private InsuranceService  insuranceService;
    
  
    public InsuranceController(InsuranceService insuranceService){
        this.insuranceService = insuranceService;
    }

    @GetMapping("/Insurance")
    public String listInsurances(Model model){

        List<InsuranceDto> insurances = insuranceService.findAllInsurance();
        model.addAttribute("insurances", insurances);
        return "admin/insurance/list-insurance";
    }

    @GetMapping("/user-insurance")
    public String listUserInsurances(Model model){

        List<InsuranceDto> insurances = insuranceService.findAllInsurance();
        model.addAttribute("insurances", insurances);
        return "user/lifeOffer/life-offer";
    }
    @GetMapping("/user-vehicle")
    public String listUserVehicle(Model model){
        List<InsuranceDto> insurances = insuranceService.findAllVehicle();
        model.addAttribute("insurances", insurances);
        return "user/vehicleOffer/vehicle-offer";
    }

    @GetMapping("/vehicle")
    public String listVehicle(Model model){
        List<InsuranceDto> insurances = insuranceService.findAllVehicle();
        model.addAttribute("insurances", insurances);
        return "admin/insurance/list-vehicle";
    }

    @GetMapping("/insurance/new")
    public String createInsuranceForm(Model model) {
        Insurance insurance = new Insurance();
        model.addAttribute("insurance", insurance);
        return "admin/insurance/insurance-create";
    }
    @PostMapping("/insurance/new")
    public String saveInsurance(@Valid @ModelAttribute("insurance") InsuranceDto insuranceDto, BindingResult result, Model model){
        if(result.hasErrors()) {
            model.addAttribute("insurance", insuranceDto);
            return "admin/insurance/insurance-create";


        }



        insuranceService.saveInsurance(insuranceDto);
        return "redirect:/Insurance";
    }

    @GetMapping("/insurance/{insuranceId}/edit")
    public String editInsuranceForm(@PathVariable("insuranceId") Long insuranceId, Model model) {
        InsuranceDto insurance = insuranceService.findInsuranceById(insuranceId);
        model.addAttribute("insurance", insurance);
        return "admin/insurance/insurance-edit";
    }

    @GetMapping("/vehicle/{vehicleId}/edit")
    public String editVehicleForm(@PathVariable("vehicleId") Long vehicleId, Model model) {
        InsuranceDto insurance = insuranceService.findInsuranceById(vehicleId);
        model.addAttribute("insurance", insurance);
        return "admin/insurance/vehicle-edit";
    }

    @PostMapping("/insurance/{insuranceId}/edit")
        public String updateInsurance(@PathVariable("insuranceId") Long insuranceId,@Valid @ModelAttribute("insurance") InsuranceDto insurance,BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("insurance", insurance);
            return "admin/insurance/insurance-edit";
        }
        insurance.setId(insuranceId);
            insuranceService.updateInsurance(insurance);
        return "redirect:/Insurance";
    }
    @PostMapping("/vehicle/{vehicleId}/edit")
    public String updateVehicle(@PathVariable("vehicleId") Long vehicleId, @ModelAttribute("insurance") InsuranceDto insuranceDto) {


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
