package com.example.crudtest.service;

import com.example.crudtest.model.Memouser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface MemoUserService extends UserDetailsService {
    Optional<Memouser> selectOneUser(Memouser user);
}
