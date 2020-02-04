package com.example.crudtest.config;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public abstract class DatabaseConfiguration {
    protected void getDataSource(String url, String driverClassName, String username, String password, String disabledLogList) {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(url);
        hikariDataSource.setDriverClassName(driverClassName);
        hikariDataSource.setUsername(username);
        hikariDataSource.setPassword(password);
        hikariDataSource.setMaximumPoolSize(10);

        hikariDataSource.setConnectionTestQuery("SELECT 1 FROM DUAL");
        hikariDataSource.setMaxLifetime(600*1000);
        hikariDataSource.setConnectionTimeout(120*1000);
        hikariDataSource.setLeakDetectionThreshold(60*1000);
        hikariDataSource.setIdleTimeout(60*1000);
        hikariDataSource.setMinimumIdle(5);

//        Log4jdbcProxyDataSource logDataSource = new Log4jdbcProxyDataSource(hikariDataSource);
    }
}
