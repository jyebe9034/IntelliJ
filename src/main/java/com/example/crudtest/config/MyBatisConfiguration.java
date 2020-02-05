package com.example.crudtest.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;

public abstract class MyBatisConfiguration {
    public static final String TYPE_ALIASES_PACKAGE = "com.example";

    public static final String MAPPER_LOCATION_PATH = "classpath:mybatis/*.xml";

    protected void configureSqlSessionFactory(DataSource dataSource, SqlSessionFactoryBean sessionFactoryBean) throws IOException{
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setJdbcTypeForNull(JdbcType.NULL);

        PathMatchingResourcePatternResolver pathResolver = new PathMatchingResourcePatternResolver();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setTypeAliasesPackage(TYPE_ALIASES_PACKAGE); // @Alias 읽기 세팅
        sessionFactoryBean.setMapperLocations(pathResolver.getResource(MAPPER_LOCATION_PATH));
        // 페이징 처리 인터셉터는 안가져 옴.
        sessionFactoryBean.setConfiguration(configuration);
        sessionFactoryBean.setVfs(SpringBootVFS.class);
    }
}

@Configuration
//@MapperScan(basePackages="com.example.**.dao", annotationClass = Repository.class, sqlSessionFactoryRef = "defaultSqlSessionFactory")
@Lazy
@EnableTransactionManagement
@MapperScan(basePackages = {"com.example.**.dao"})
class DefaultMyBatisConfiguration extends MyBatisConfiguration {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("defaultDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setTypeAliasesPackage("com.example.crudtest.dao");
        sessionFactoryBean.setConfigLocation(applicationContext.getResource(MAPPER_LOCATION_PATH));
        sessionFactoryBean.setMapperLocations(applicationContext.getResources(MAPPER_LOCATION_PATH));
        return sessionFactoryBean.getObject();
    }

//    @Primary
//    @Bean
//    public SqlSessionTemplate defaultSqlSession(@Qualifier("defaultSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
//        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
//        return sqlSessionTemplate;
//    }
//
//    @Primary
//    @Bean
//    public SqlSessionFactory defaultSqlSessionFactory(@Qualifier("defaultDataSource") DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
//        configureSqlSessionFactory(dataSource, sessionFactoryBean);
//        return sessionFactoryBean.getObject();
//    }
}
