package com.zjq.batchdemo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
  * @Description: 目标数据源配置
  * @Author: zhangjunqiang
  * @Date: 2022/4/10 17:10
  * @version v1.0
  */
@Configuration
@MapperScan(basePackages = "com.zjq.batchdemo.dao.targets",sqlSessionFactoryRef = "targetSqlSessionFactory")
public class TargetDataSourceConfig {
    @Bean(name = "targetDatasource")
    @ConfigurationProperties(prefix = "spring.target-datasource")
    public DataSource targetDatasource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "targetSqlSessionFactory")
    public SqlSessionFactory targetSqlSessionFactory(@Qualifier("targetDatasource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/targets/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "targetSqlSessionTemplate")
    public SqlSessionTemplate targetSqlSessionTemplate(@Qualifier("targetSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
