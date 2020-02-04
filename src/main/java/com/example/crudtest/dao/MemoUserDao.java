package com.example.crudtest.dao;

import com.example.crudtest.model.MemoUserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoUserDao {

    MemoUserDetails selectAccountForLogin(String userId);
}
