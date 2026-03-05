package com.library.backend.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AuthorizationUtil {

    //Check if user is authorized to access resource
    public static void checkUserAccess(HttpServletRequest request, Integer targetUserId) {
        Integer loggedInUserid = (Integer) request.getAttribute("userId");
        String role = (String) request.getAttribute("userRole");

        if (loggedInUserid == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not authenticated");
        }

        // Admin can access everything
        if ("admin".equalsIgnoreCase(role)) {
            return;
        }

        // User can only access their own data
        if (!loggedInUserid.equals(targetUserId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
        }
    }

    // Check if user is admin
    public static void checkAdminAccess(HttpServletRequest request) {
        String role = (String) request.getAttribute("userRole");

        if (role == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not authenticated");
        }

        if (!"admin".equalsIgnoreCase(role)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Admin access required");
        }
    }
}
