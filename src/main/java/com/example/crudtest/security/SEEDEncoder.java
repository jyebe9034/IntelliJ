package com.example.crudtest.security;

import org.springframework.security.crypto.password.PasswordEncoder;

public class SEEDEncoder implements PasswordEncoder {
    /**
     * SEED SHA-256 으로 인코딩
     * @param rawPassword
     * @return 인코딩된 문자열
     */
    @Override
    public String encode(CharSequence rawPassword) {
        return KISA_SHA256.encrypt(rawPassword.toString());
    }

    /**
     * 암호 비교
     * @param rawPassword
     * @param encodedPassword
     * @return
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if(encodedPassword.equals(encode(rawPassword))) return true;
        else return false;
    }
}
