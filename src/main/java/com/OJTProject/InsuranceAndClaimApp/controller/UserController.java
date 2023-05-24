package com.OJTProject.InsuranceAndClaimApp.controller;

import com.OJTProject.InsuranceAndClaimApp.config.FileUploadUtil;
import com.OJTProject.InsuranceAndClaimApp.dto.UserDto;
import com.OJTProject.InsuranceAndClaimApp.model.User;
import com.OJTProject.InsuranceAndClaimApp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // handler method to handle home page request
    @GetMapping("/user/index1")
    public String home1(){
        return "/user/index";
    }

    // handler method to handle user registration form request
    @GetMapping("/register1")
    public String showRegistrationForm1(Model model){
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "user/register";
    }

    // handler method to handle user registration form submit request
//    @PostMapping("/register/save1")
//    public String registration1(@Valid @ModelAttribute("user") UserDto userDto,
//                               BindingResult result,
//                               Model model){
//        User existingUser = userService.findUserByEmail(userDto.getEmail());
//
//        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
//            result.rejectValue("email", null,
//                    "There is already an account registered with the same email");
//        }
//
//        if(result.hasErrors()){
//            model.addAttribute("user", userDto);
//            return "/register1";
//        }
//
//        userService.saveUser1(userDto);
//        return "redirect:/register1?success";
//    }

    @PostMapping("/register/save1")
    public String userRegistration(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model,
                                   @RequestParam("image") MultipartFile multipartFile) throws IOException {

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
            return "user/register";
        }


        userService.saveUser1(userDto);
        return "redirect:/register1?success";
    }

    // handler method to handle list of users
    @GetMapping("/users1")
    public String users1(Model model){
        List<UserDto> users = userService.findAllUsers1();
        model.addAttribute("users", users);
        return "user/users";
    }

    // handler method to handle login request
    @GetMapping("/login1")
    public String login1(){
        return "user/login";
    }

}
