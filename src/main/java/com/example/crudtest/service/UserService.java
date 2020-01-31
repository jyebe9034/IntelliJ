package com.example.crudtest.service;

import com.example.crudtest.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> selectOneUser(User user);
}
