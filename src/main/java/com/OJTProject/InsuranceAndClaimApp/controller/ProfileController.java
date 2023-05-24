package com.OJTProject.InsuranceAndClaimApp.controller;

import com.OJTProject.InsuranceAndClaimApp.config.CustomUserDetails;
import com.OJTProject.InsuranceAndClaimApp.model.User;
import com.OJTProject.InsuranceAndClaimApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
@Controller
public class ProfileController {

   @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model){
        String email = loggedUser.getUsername();
        User user = userService.getByEmail(email);
        model.addAttribute("user",user);
        return "admin/user/profile";
    }
}
