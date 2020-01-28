package com.example.crudtest;

import com.example.crudtest.model.Memo;
import com.example.crudtest.service.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@SpringBootApplication
public class CrudtestApplication {

    @Autowired
    private MemoService memo;

    @GetMapping("/api/getData")
    public ResponseEntity<List<Memo>> getData() {
        return ResponseEntity.ok(this.memo.selectMemoList());
    }

    @PostMapping("/api/createData")
    public ResponseEntity<Map<String, String>> createData(@RequestBody Memo memo) {
        this.memo.insertMemo(memo);
        Map<String, String> map = new HashMap<>();
        map.put("result", "success");

        return ResponseEntity.ok(map);
    }

    @PutMapping("/api/editData")
    public ResponseEntity<Map<String, String>> editData(@RequestBody Memo memo) {
        this.memo.updateMemo(memo);
        Map<String, String> map = new HashMap<>();
        map.put("result", "success");

        return ResponseEntity.ok(map);
    }

    @DeleteMapping("/api/deleteData/{seq}")
    public ResponseEntity<Map<String, String>> deleteData(@PathVariable Long seq) {
        this.memo.deleteMemo(seq);
        Map<String, String> map = new HashMap<>();
        map.put("result", "success");

        return ResponseEntity.ok(map);
    }

    public static void main(String[] args) {
        SpringApplication.run(CrudtestApplication.class, args);
    }

// TODO: 프로파일

}
