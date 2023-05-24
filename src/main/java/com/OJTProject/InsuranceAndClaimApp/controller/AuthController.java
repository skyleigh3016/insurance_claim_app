package com.OJTProject.InsuranceAndClaimApp.controller;

import com.OJTProject.InsuranceAndClaimApp.config.CustomUserDetails;
import com.OJTProject.InsuranceAndClaimApp.config.FileUploadUtil;
import com.OJTProject.InsuranceAndClaimApp.dto.ClaimDto;
import com.OJTProject.InsuranceAndClaimApp.dto.ResponseDto;
import com.OJTProject.InsuranceAndClaimApp.dto.UserDto;

import com.OJTProject.InsuranceAndClaimApp.model.Claim;
import com.OJTProject.InsuranceAndClaimApp.model.User;
import com.OJTProject.InsuranceAndClaimApp.repository.UserRepository;

import com.OJTProject.InsuranceAndClaimApp.service.ClaimService;
import com.OJTProject.InsuranceAndClaimApp.service.ClientService;
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
public class AuthController {
    private UserService userService;
    private ClaimService claimService;

    private ClientService clientService;
    @Autowired
    private UserRepository userRepository;

    public AuthController(UserService userService, ClaimService claimService, ClientService clientService) {
        this.userService = userService;
        this.claimService = claimService;
        this.clientService = clientService;
    }

    // handler method to handle home page request
    @GetMapping("/index")
    public String home(){
        return "index";
    }

    // handler method to handle user registration form request
    @GetMapping("/user/register")
    public String userRegistrationForm(Model model){
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "admin/user/user-create";
    }

    // handler method to handle user registration form request
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle user registration form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model,
                               @RequestParam("image") MultipartFile multipartFile) throws IOException{

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        userDto.setAvatar(fileName);

//        User savedUser = userRepository.save(User);

        String uploadDir = "user-photos/";

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }


        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

//    @PostMapping("/user/save")
//    public String userRegistration(@RequestParam MultipartFile avatar,@Valid @ModelAttribute("user") UserDto userDto, BindingResult result,
//                                    Model model){
//
//
//        User existingUser1 = userService.findUserByEmail(userDto.getEmail());
//
//        if(existingUser1 != null && existingUser1.getEmail() != null && !existingUser1.getEmail().isEmpty()){
//            result.rejectValue("email", null,
//                    "There is already an account registered with the same email");
//        }
//
//        if(result.hasErrors()){
//            model.addAttribute("user", userDto);
//            return "admin/user/user-create";
//        }
//
//
//        userService.saveUser(userDto);
//
//
//
//
//            try {
//
//                File saveFile = new ClassPathResource("static/img").getFile();
//
//                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + avatar.getOriginalFilename());
//                //System.out.println(path);
//                Files.copy(avatar.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        return "redirect:/user/register?success";
//    }

    @PostMapping("/user/save")
    public String userRegistration(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model,
                                   @RequestParam("image") MultipartFile multipartFile) throws IOException{

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        userDto.setAvatar(fileName);

//        User savedUser = userRepository.save(User);

        String uploadDir = "user-photos/";

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);


        User existingUser1 = userService.findUserByEmail(userDto.getEmail());

        if(existingUser1 != null && existingUser1.getEmail() != null && !existingUser1.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "admin/user/user-create";
        }


        userService.saveUser1(userDto);
        return "redirect:/user/register?success";
    }


    // handler method to handle list of users
    @GetMapping("/users")
    public String users(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "admin/user/list-user";
    }
    // handler method to handle login request
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/dashboard")
    public String Home(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model){
        long id = loggedUser.getId();
        String email = loggedUser.getUsername();
        long claims = claimService.findAllUserClaims1(email);

        if(claims != 0){
            List<ClaimDto> claimDtos = claimService.findAllUserClaims(id);
            long users = userService.countUsers();
            long life = userService.countLife();
            long amount = userService.totalAmount();
            long myAmount = userService.totalMyAmount(email);
            long vehicle = userService.countVehicle();
            List<ResponseDto> clients = clientService.findAllClientInfo();
            model.addAttribute("clients", clients);
            model.addAttribute("amount", amount);
            model.addAttribute("myAmount", myAmount);
            model.addAttribute("users", users);
            model.addAttribute("life", life);
            model.addAttribute("vehicle", vehicle);
            model.addAttribute("claims", claimDtos);
            return "admin/index";

        }

        List<ClaimDto> claimDtos = claimService.findAllUserClaims(id);

        long users = userService.countUsers();
        long life = userService.countLife();
        long amount = userService.totalAmount();
//        long myAmount = userService.totalMyAmount(email);
        long vehicle = userService.countVehicle();
        List<ResponseDto> clients = clientService.findAllClientInfo();
        model.addAttribute("clients", clients);
        model.addAttribute("amount", amount);
//        model.addAttribute("myAmount", myAmount);
        model.addAttribute("users", users);
        model.addAttribute("life", life);
        model.addAttribute("vehicle", vehicle);
        model.addAttribute("claims", claimDtos);
        return "admin/index";

    }



    @GetMapping("/user/{userId}/delete")
    public String deleteUser(@PathVariable("userId")Long userId) {
        userService.delete(userId);
        return "redirect:/users";
    }

}
