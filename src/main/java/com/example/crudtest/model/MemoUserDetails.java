package com.example.crudtest.model;

import org.apache.ibatis.type.Alias;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Alias("memoUserDetails")
public class MemoUserDetails extends MemoUser implements UserDetails {

    public MemoUserDetails() {
    }

    public MemoUserDetails(String email, Collection<? extends GrantedAuthority> authorities) {
        this.setEmail(email);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public String getPassword() {
        return getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        // 계정 만료에 관한 정책
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 계정 잠금에 관한 정책
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 인증 정보 만료에 관한 정책
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 계정 활성상태에 관한 정책
        return true;
    }
}
