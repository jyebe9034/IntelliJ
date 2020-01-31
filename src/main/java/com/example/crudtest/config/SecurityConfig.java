package com.example.crudtest.config;

import com.example.crudtest.security.AdminAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AdminAuthenticationEntryPoint securityAuthenticationEntryPoint;

//    @Autowired
//    private AdminAuthenticationSuccessHandler adminAuthenticationSuccessHandler;
//
//    @Autowired
//    private AdminAuthenticationFailureHandler adminAuthenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(securityAuthenticationEntryPoint)
                .and()
                .headers()
                .disable()
                .formLogin()
                .loginProcessingUrl("/api/login");
//                .successHandler(adminAuthenticationSuccessHandler)
//                .failureHandler(adminAuthenticationFailureHandler)
    }
}
