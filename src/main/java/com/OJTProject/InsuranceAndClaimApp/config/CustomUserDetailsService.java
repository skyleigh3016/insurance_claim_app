package com.OJTProject.InsuranceAndClaimApp.config;

//import com.example.registrationlogindemo.entity.Role;
//import com.example.registrationlogindemo.entity.User;
//import com.example.registrationlogindemo.repository.UserRepository;
import com.OJTProject.InsuranceAndClaimApp.model.Role;
import com.OJTProject.InsuranceAndClaimApp.model.User;
import com.OJTProject.InsuranceAndClaimApp.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user != null) {
            return new CustomUserDetails(user);
        }else{
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

//    private Collection < ? extends GrantedAuthority> mapRolesToAuthorities(Collection <Role> roles) {
//        Collection < ? extends GrantedAuthority> mapRoles = roles.stream()
//                .map(role -> new SimpleGrantedAuthority(role.getName()))
//                .collect(Collectors.toList());
//        return mapRoles;
//    }
}
