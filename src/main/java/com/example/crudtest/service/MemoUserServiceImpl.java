package com.example.crudtest.service;

import com.example.crudtest.dao.MemoUserDao;
import com.example.crudtest.model.MemoUser;
import com.example.crudtest.model.MemoUserDetails;
import com.example.crudtest.repo.MemouserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemoUserServiceImpl implements MemoUserService {

    @Autowired
    private MemoUserDao userDao;

    @Autowired
    private MemouserRepo userRepo;

    @Override
    public Optional<MemoUser> selectOneUser(MemoUser user) { return this.userRepo.findById(user.getEmail()); }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemoUserDetails user = userDao.selectAccountForLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException(username + "라는 사용자가 없습니다.");
        }
        return user;
    }
}
