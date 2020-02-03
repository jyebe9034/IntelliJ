package com.example.crudtest.config;

import com.example.crudtest.security.AdminAuthenticationEntryPoint;
import com.example.crudtest.security.AdminAuthenticationFailureHandler;
import com.example.crudtest.security.AdminAuthenticationSuccessHandler;
import com.example.crudtest.security.SEEDEncoder;
import com.example.crudtest.service.MemoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String REMEMBER_KEY = "!JWT0$header_s5ecret";

    @Autowired
    private MemoUserService memoUserService;

    @Autowired
    private AdminAuthenticationEntryPoint securityAuthenticationEntryPoint;

    @Autowired
    private AdminAuthenticationSuccessHandler adminAuthenticationSuccessHandler;

    @Autowired
    private AdminAuthenticationFailureHandler adminAuthenticationFailureHandler;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

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
                .loginProcessingUrl("/api/login")
                .successHandler(adminAuthenticationSuccessHandler)
                .failureHandler(adminAuthenticationFailureHandler)
                .usernameParameter("email")
                .passwordParameter("password")
                .permitAll()
                .and()
                .rememberMe()
                .key(REMEMBER_KEY)
                .rememberMeServices(tokenBasedRememberMeServices())
                .and()
                .logout()
                .logoutUrl("/api/logout")
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
                .deleteCookies("JSESSIONID", "remember-me", "jwt-header")
                .and()
                .authorizeRequests()
                .antMatchers("/api/**").authenticated();

    }

    @Bean
    public TokenBasedRememberMeServices tokenBasedRememberMeServices() {
        TokenBasedRememberMeServices tokenBasedRememberMeServices = new TokenBasedRememberMeServices(REMEMBER_KEY, memoUserService) {
            @Override
            protected String extractRememberMeCookie(HttpServletRequest request) {
                // 쿠키가 아닌 헤더에서 값 가져옴
                return request.getHeader(getCookieName());
            }

            @Override
            protected void setCookie(String[] tokens, int maxAge, HttpServletRequest request, HttpServletResponse response) {
                String cookieValue = encodeCookie(tokens);

                // 쿠키가 아닌 헤더에 값을 세팅
                response.setHeader(getCookieName(), cookieValue);
            }
        };
        tokenBasedRememberMeServices.setAlwaysRemember(true);        // 항상 로그인시 리멤버미 서비스 발동
        tokenBasedRememberMeServices.setCookieName("jwt-header");    // 리멤버미 헤더 이름
        return tokenBasedRememberMeServices;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(seedEncoder());
        authenticationProvider.setUserDetailsService(memoUserService);
        return authenticationProvider;
    }

    @Bean
    public SEEDEncoder seedEncoder() {
        SEEDEncoder seedEncoder = new SEEDEncoder();
        return seedEncoder;
    }
}
