package com.OJTProject.InsuranceAndClaimApp.controller;

import com.OJTProject.InsuranceAndClaimApp.config.CustomUserDetails;
import com.OJTProject.InsuranceAndClaimApp.config.FileUploadUtil;
import com.OJTProject.InsuranceAndClaimApp.dto.ClaimDto;

import com.OJTProject.InsuranceAndClaimApp.dto.ResponseDto;
import com.OJTProject.InsuranceAndClaimApp.dto.ResponseUserClaimDto;
import com.OJTProject.InsuranceAndClaimApp.model.Claim;

import com.OJTProject.InsuranceAndClaimApp.model.User;
import com.OJTProject.InsuranceAndClaimApp.repository.PdfFileRepository;
import com.OJTProject.InsuranceAndClaimApp.repository.UserRepository;
import com.OJTProject.InsuranceAndClaimApp.service.ClaimService;

import com.OJTProject.InsuranceAndClaimApp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class ClaimController {
    private final PdfFileRepository pdfFileRepository;

    private final UserRepository userRepository;
    private UserService userService;

    private ClaimService claimService;
    public ClaimController( UserService userService,ClaimService claimService, PdfFileRepository pdfFileRepository, UserRepository userRepository){
        this.pdfFileRepository = pdfFileRepository;
        this.userRepository = userRepository;
        this.claimService = claimService;
        this.userService = userService;
    }
    @GetMapping("/claims")
    public String listClaims(@AuthenticationPrincipal CustomUserDetails loggedUser,Model model){
        String email = loggedUser.getUsername();
        User user = userService.getByEmail(email);
        List<ClaimDto> claims = claimService.findAllClaims();
        model.addAttribute("user", user);
        model.addAttribute("claims", claims);
        model.addAttribute("files", pdfFileRepository.findAll());
        return "admin/claim/list-claimed";
    }

    @GetMapping("/user-claims")
    public String listUserClaims(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model){

        String email = loggedUser.getUsername();
        User user = userService.getByEmail(email);

        long id = loggedUser.getId();
        List<ClaimDto> claims = claimService.findAllUserClaims(id);
        model.addAttribute("user", user);
        model.addAttribute("claims", claims);
        model.addAttribute("files", pdfFileRepository.findAll());
        return "user/myClaim/my-claim";
    }

    @GetMapping("/user-claim/new")
    public String createUserClaimForm(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model) {
        String email = loggedUser.getUsername();
        User user = userService.getByEmail(email);
        Claim claim = new Claim();
        model.addAttribute("claim", claim);
        model.addAttribute("user", user);
        return "user/myClaim/apply-claim";
    }
    @GetMapping("/claim/new")
    public String createClaimForm(Model model) {
        Claim claim = new Claim();
        model.addAttribute("claim", claim);
        return "admin/claim/create-claim";
    }

    @PostMapping("/user-claim/new")
    public String saveUserClaim(@Valid @ModelAttribute("claim") ClaimDto claimDto, BindingResult result,
                                @AuthenticationPrincipal CustomUserDetails loggedUser,Model model,
                                @RequestParam("file") MultipartFile multipartFile) throws IOException {



        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        claimDto.setDocument(fileName);


        String uploadDir = "user-photos/";

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        if(result.hasErrors()) {
            model.addAttribute("claim", claimDto);
            return "admin/claim/create-claim";


        }
        long userId = loggedUser.getId();
        claimService.saveUserClaim(claimDto, userId);
        return "redirect:/user-claims";
    }
    @PostMapping("/claim/new")
    public String saveInsurance(@Valid @ModelAttribute("claim") ClaimDto claimDto, BindingResult result, Model model,
                                @RequestParam("file") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        claimDto.setDocument(fileName);


        String uploadDir = "user-photos/";

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        if(result.hasErrors()) {
            model.addAttribute("claim", claimDto);
            return "admin/claim/create-claim";


        }
        claimService.saveClaim(claimDto);
        return "redirect:/claims";
    }

    @GetMapping("/claims/{claimId}/delete")
    public String deleteClaim(@PathVariable("claimId")Long claimId) {
        claimService.delete(claimId);
        return "redirect:/claims";
    }

    @GetMapping("/claim/{claimId}/edit")
    public String editClaimForm(@PathVariable("claimId") Long claimId, Model model) {
        ClaimDto claimDto = claimService.findClaimById(claimId);
        model.addAttribute("claim", claimDto);
        return "admin/claim/edit-claim";
    }
    @PostMapping("/claim/{claimId}/edit")
    public String updateClaim(@PathVariable("claimId") Long claimId,
                              @Valid @ModelAttribute("claim") ClaimDto claimDto, BindingResult result, Model model,
                              @RequestParam("file") MultipartFile multipartFile) throws IOException {


        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        claimDto.setDocument(fileName);


        String uploadDir = "user-photos/";

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);


        if(result.hasErrors()) {
            model.addAttribute("claim", claimDto);
            return "admin/claim/edit-claim";
        }
        claimDto.setId(claimId);
        claimService.updateClaim(claimDto);
        return "redirect:/claims";
    }
}
