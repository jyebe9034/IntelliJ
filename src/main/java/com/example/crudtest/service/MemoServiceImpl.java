package com.example.crudtest.service;


import com.example.crudtest.model.Memo;
import com.example.crudtest.repo.MemoRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
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
    private MemoRepo memo;

    /**
     * select all
     * @return
     */
    public List<Memo> selectMemoList() {
        return this.memo.findAll();
    }

    /**
     * insert memo
     * @param memo
     * @return
     */
    public void insertMemo(Memo memo) { this.memo.save(memo); }

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
