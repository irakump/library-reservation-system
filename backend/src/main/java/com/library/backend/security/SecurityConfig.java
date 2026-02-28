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
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry -> {
                    // Public endpoints - anyone can access without login
                    registry.requestMatchers("/api/auth/register").permitAll();
                    registry.requestMatchers("/api/auth/login").permitAll();
                    registry.requestMatchers("/api/book/**").permitAll();
                    registry.requestMatchers("/api/genre/**").permitAll();
                    registry.requestMatchers("/api/language/**").permitAll();
                    registry.requestMatchers("/api/author/**").permitAll();

                    // Protected endpoints - require JWT token
                    registry.requestMatchers("/api/user**").authenticated();
                    registry.requestMatchers("/api/loans/**").authenticated();
                    registry.requestMatchers("/api/reservations/**").authenticated();
                    registry.requestMatchers("/api/favorite/**").authenticated();

                    // All other endpoints require authentication
                    registry.anyRequest().authenticated();
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    // Hash password (using Spring Security)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
