package com.example.crudtest.repo;

import com.example.crudtest.model.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepo extends JpaRepository<Memo, Long> {
}
