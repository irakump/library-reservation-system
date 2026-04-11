package com.library.backend.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Utility class for authorization checks.
 */
public class AuthorizationUtil {

    /**
     * Checks if user is authorized to access resource.
     *
     * @param request HTTP request containing user attributes
     * @param targetUserId target user id
     */
    public static void checkUserAccess(final HttpServletRequest request,
                                       final Integer targetUserId) {
        Integer loggedInUserid = (Integer) request.getAttribute("userId");
        String role = (String) request.getAttribute("userRole");

        if (loggedInUserid == null) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Not authenticated");
        }

        // Admin can access everything
        if ("admin".equalsIgnoreCase(role)) {
            return;
        }

        // User can only access their own data
        if (!loggedInUserid.equals(targetUserId)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "Access denied");
        }
    }

    /**
     * Checks if user has admin access.
     *
     * @param request HTTP request containing user attributes
     */
    public static void checkAdminAccess(final HttpServletRequest request) {
        String role = (String) request.getAttribute("userRole");

        if (role == null) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Not authenticated");
        }

        if (!"admin".equalsIgnoreCase(role)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "Admin access required");
        }
    }
}
