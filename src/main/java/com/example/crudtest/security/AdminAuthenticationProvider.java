package com.example.crudtest.security;

import com.example.crudtest.model.MemoUserDetails;
import com.example.crudtest.service.MemoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

public class AdminAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private SEEDEncoder seedEncoder;

    @Autowired
    private MemoUserService memoUserService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        if(StringUtils.isEmpty(password)) {
            throw new BadCredentialsException("password required");
        }

        UserDetails userDetails = memoUserService.loadUserByUsername(username);

        if(userDetails == null) {
            throw new BadCredentialsException("not exist user");
        }

        // if(!seedEncoder.matches(userDetails.getPassword(), seedEncoder.encode(userDetails.getPassword()))) {}

        MemoUserDetails memoUserDetails = (MemoUserDetails) userDetails;

        return new UsernamePasswordAuthenticationToken(userDetails, password, null);
    }

    @Override
    public boolean supports(Class<?> authentication) { return true; }
}
