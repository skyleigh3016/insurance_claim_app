package com.OJTProject.InsuranceAndClaimApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@Order(1)
@EnableWebSecurity
public class SpringSecurity {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/register/**","/register1/**").permitAll()
                                .requestMatchers("/index").permitAll()
                                .requestMatchers("/dashboard","/profile", "/img/**", "/css/**", "/js/**","static/img","/user-photos/","/user-photos/**").hasAnyRole("ADMIN","USER")
                                .requestMatchers("/users","/user/register", "/user/save","/user/{userId}/delete","/vehicle","/client/{clientId}/view",
                                        "/Insurance","/insurance/new","/insurance/{insuranceId}/edit","/insurances/{insuranceId}/delete","/vehicle/{vehicleId}/edit","/claim/{claimId}/edit",
                                        "/ping","/display","/","/add","com.OJTProject.InsuranceAndClaimApp","/clients","/claims","/clients/{userId}/new","/clients/{userId}","/clients_info",
                                        "/imageUpload","/indexSample","static/img","/user-photos/","/user-photos/**","/claim/new","/claims/{claimId}/delete","/clients/{userId}","/claim/{claimId}/edit",
                                        "/img/**", "/css/**", "/js/**").hasRole("ADMIN")

                                .requestMatchers("/users1","/dashboard","/my-insurance","/user-claims","/user-claim/new","/user-insurance", "/user-vehicle","/img/**", "/css/**", "/js/**").hasRole("USER")


                ).formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/dashboard")
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );
        return http.build();
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
