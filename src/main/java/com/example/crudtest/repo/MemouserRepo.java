package com.example.crudtest.repo;

import com.example.crudtest.model.MemoUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemouserRepo extends JpaRepository<MemoUser, String> {
}
