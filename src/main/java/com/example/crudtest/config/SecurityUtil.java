package com.example.crudtest.config;

import com.example.crudtest.model.MemoUserDetails;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component("securityUtil")
public class SecurityUtil {

    public static boolean isAnonymous() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return false;
        }
        return AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass());
    }

    public static boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return false;
        }
        return !isAnonymous();
    }

    /**
     * Get the login of the current user.
     *
     * @return the login of the current user
     */
    private static UserDetails getCurrentUser() {
        if (isAuthenticated()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null) {
                return null;
            }
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }

    /**
     * Get the login of the current account (user).
     *
     * @return the login of the current account
     */
    public static MemoUserDetails getCurrentAccount() {
        UserDetails userDetails = getCurrentUser();
        MemoUserDetails member = null;
        if(userDetails != null && userDetails instanceof MemoUserDetails) {
            member = (MemoUserDetails) userDetails;
        }
        return member;
    }


    /**
     * Programmatically signs in the user with the given the user ID.
     */
    public static void signin(UserDetails detail, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(detail, null, detail.getAuthorities());
        token.setDetails(new WebAuthenticationDetails(request));

        SecurityContextHolder.getContext().setAuthentication(token);
    }

    /**
     * Signout
     */
    public static void signout() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}
