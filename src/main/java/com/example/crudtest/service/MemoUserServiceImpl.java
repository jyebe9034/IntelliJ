package com.example.crudtest.service;

import com.example.crudtest.model.Memouser;
import com.example.crudtest.repo.MemouserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class MemoUserServiceImpl implements MemoUserService {

    @Autowired
    private MemouserRepo userRepo;

    @Override
    public Optional<Memouser> selectOneUser(Memouser user) { return this.userRepo.findById(user.getEmail()); }
}
