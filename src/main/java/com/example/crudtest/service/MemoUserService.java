package com.example.crudtest.service;

import com.example.crudtest.model.MemoUser;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface MemoUserService extends UserDetailsService {
    Optional<MemoUser> selectOneUser(MemoUser user);
}
