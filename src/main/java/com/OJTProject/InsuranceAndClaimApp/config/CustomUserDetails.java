package com.OJTProject.InsuranceAndClaimApp.config;

import com.OJTProject.InsuranceAndClaimApp.model.Role;
import com.OJTProject.InsuranceAndClaimApp.model.User;

import jakarta.persistence.Transient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    private User user;

    public CustomUserDetails(User user) {
        super();
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Role> roles =  user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;

    }


    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return user.getPassword();
    }


    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
    @Transient
    public String getAvatar() {
        return "/user-photos/" + user.getPhotosImagePath();
    }

    public long getId() {
        return user.getId();
    }
    public String getFullName() {
        return user.getName();
    }
    public String getBarangay() {
        return user.getBarangay();
    }
    public String getMunicipality() {
        return user.getMunicipality();
    }
    public String getState() {
        return user.getState();
    }
    public String getGender() {
        return user.getGender();
    }
    public int getUserAge() {
        return user.getAge();
    }
    public LocalDate getBirthDate() {
        return user.getBirthday();
    }

    public LocalDateTime getRegisteredDate() {
        return user.getCreatedOn();
    }
    @Transient
    public String getPhotosImagePath() {
        if (getAvatar() == null || user.getId() == null) return null;

        return "/user-photos/" + getAvatar();
    }

}
