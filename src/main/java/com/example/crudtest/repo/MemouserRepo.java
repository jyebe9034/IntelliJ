package com.example.crudtest.repo;

import com.example.crudtest.model.Memouser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemouserRepo extends JpaRepository<Memouser, String> {
}
