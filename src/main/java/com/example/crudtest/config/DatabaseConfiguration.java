package com.example.crudtest.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import net.sf.log4jdbc.Log4jdbcProxyDataSource;
import net.sf.log4jdbc.tools.Log4JdbcCustomFormatter;
import net.sf.log4jdbc.tools.LoggingType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

public abstract class DatabaseConfiguration {
    protected DataSource getDataSource(String url, String driverClassName, String username, String password, String disabledLogList) {
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

        Log4jdbcProxyDataSource logDataSource = new Log4jdbcProxyDataSource(hikariDataSource);

        Log4JdbcCustomFormatter formatter = new DmcorpLog4JdbcCustomFormatter(disabledLogList);
        formatter.setLoggingType(LoggingType.MULTI_LINE);
        logDataSource.setLogFormatter(formatter);
        return logDataSource;
    }
}

@Configuration
@EnableTransactionManagement
@Getter
@Setter
@ConfigurationProperties(prefix="spring.datasource")
class DefaultDatabaseConfiguration extends DatabaseConfiguration {
    private String jdbcUrl;
    private String type;
    private String driverClassName;
    private String username;
    private String password;
    private String disabledLogList;

    @Primary
    @Bean(name = "defaultDataSource")
    public DataSource dataSource() {
        return getDataSource(jdbcUrl, driverClassName, username, password, disabledLogList);
    }

    @Primary
    @Bean(name = "defaultTx")
    public PlatformTransactionManager transactionManager(@Qualifier("defaultDataSource") DataSource defaultDataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(defaultDataSource);
//        transactionManager.setGlobalRollbackOnParticipationFailure(false);
        return transactionManager;
    }
}