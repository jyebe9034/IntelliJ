package com.example.crudtest.security;

import com.example.crudtest.model.MemoUserDetails;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class AdminAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        log.info("로그인 성공....");


        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        MemoUserDetails memoUserDetails = SecurityUtil.getCurrentAccount();
        memoUserDetails.setPassword(null);
        Map<String, MemoUserDetails> result = new HashMap<>();
        result.put("memoUser", memoUserDetails);

        response.getWriter().write(new Gson().toJson(result));
    }
}
