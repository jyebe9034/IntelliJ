package com.example.crudtest.service;

import com.example.crudtest.model.Memo;

import java.util.List;

public interface MemoService {
    List<Memo> selectMemoList();

    void insertMemo(Memo memo);
}
