package com.library.backend.security;

import com.library.backend.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /*
    // TODO: use these after UserService is implemented

    @Autowired
    private final UserService userService;


    @Bean
    public UserDetailsService userDetailsService() {
        return userService();
    }



    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        //provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
    */


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry -> {
                    // Anyone can access registration REST API without login
                    registry.requestMatchers("/api/auth/register").permitAll();
                    registry.requestMatchers("/api/auth/login").permitAll();
                    registry.requestMatchers("/api/book/**").permitAll();
                    registry.requestMatchers("/api/genre/**").permitAll();
                    registry.requestMatchers("/api/language/**").permitAll();
                    registry.requestMatchers("/api/author/**").permitAll();
                    //registry.anyRequest().authenticated();  // all endpoints (except above) need login
                    registry.anyRequest().permitAll();  // TODO: remove this after login is ready! => now all endpoints are accessible without login
                })

                .build();
    }

    // Hash password (using Spring Security)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
