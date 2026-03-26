package com.library.backend.security;

import com.library.backend.user.User;
import com.library.backend.user.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // Get Authorization header
        String authHeader = request.getHeader("Authorization");

        String token = null;
        String email = null;

        // Check if header contains Bearer token
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7); // Remove "Bearer" prefix

            try {
                email = jwtUtil.extractEmail(token);
            } catch (ExpiredJwtException e) {
                logger.error("Jwt token has expired");
            } catch (MalformedJwtException e) {
                logger.error("Invalid JWT Token");
            } catch (Exception e) {
                logger.error("Error parsing JWT Token");
            }
        }


        // If we have email and no authentication set yet
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Load user from database
            User user = userRepository.findByEmail(email).orElse(null);

            if (user != null && jwtUtil.validateToken(token, email)) {

                // Create authentication token
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        email, null,
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().toString().toUpperCase()))
                );

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Set authentication in Spring Security context
                SecurityContextHolder.getContext().setAuthentication(authToken);

                // Store userId in request attribute for easy access i controllers
                request.setAttribute("userId", user.getUserId());
                request.setAttribute("userRole", user.getRole().toString());
            }
        }

        // Continue filter chain
        filterChain.doFilter(request, response);

    }
}
