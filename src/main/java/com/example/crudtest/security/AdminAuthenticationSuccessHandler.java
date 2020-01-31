package com.example.crudtest.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AdminAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

//        AccountUserDetails userDetails = SecurityUtil.getCurrentAccount();
//        userDetails.setMgtPw(null);
//        Map<String, AccountUserDetails> result = new HashMap<>();
//        result.put("account", userDetails);
//
//        response.getWriter().write(new Gson().toJson(result));
    }
}
