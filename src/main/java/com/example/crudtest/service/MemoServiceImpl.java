package com.example.crudtest.service;


import com.example.crudtest.model.Memo;
import com.example.crudtest.repo.MemoRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
/*@ConfigurationProperties(prefix = "spring.datasource")*/
public class MemoServiceImpl implements MemoService {

    @Autowired
    private MemoRepo memoRepo;

    /**
     * select all
     * @return
     */
    @Override
    public List<Memo> selectMemoList() {
        return this.memoRepo.findAll();
    }

    /**
     * insert memo
     * @param memo
     * @return
     */
    @Override
    public void insertMemo(Memo memo) { this.memoRepo.save(memo); }

    /**
     * update memo
     * 업데이트를 하기 위해서는 일단 pk로 조회를 해온 다음에 saveAndFlush()하면 됨.
     * @param memo
     */
    @Override
    public void updateMemo(Memo memo) {
        Memo org = this.memoRepo.getOne(memo.getSeq());
        org.setTitle(memo.getTitle());
        org.setWriter(memo.getWriter());
        this.memoRepo.saveAndFlush(org);
    }

/*
    @Bean("msg")
    @Profile("test")
    public String msg() {
        return "test";
    }

    @Bean("msg")
    @Profile("prod")
    public String msgProd() {
        return "prod";
    }

    private String driverClassName;

    @Resource(name = "msg")
    private String msg;

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    @PostConstruct
    public void init() {
        log.info("driverClassName : " + driverClassName);
        log.info("msg : " + msg);
    }

    @PreDestroy
    public void destroy() {
        log.info("driverClassName : " + driverClassName);
    }
*/

}
