package com.example.crudtest.service;

import com.example.crudtest.model.User;
import com.example.crudtest.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public User selectOneUser(User user) { return this.userRepo.getOne(user.getEmail()); }
}
