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
  * @Description: 源数据源配置
  * @Author: zhangjunqiang
  * @Date: 2022/4/10 17:10
  * @version v1.0
  */
@Configuration
@MapperScan(basePackages = "com.zjq.batchdemo.dao.origin",sqlSessionFactoryRef = "originSqlSessionFactory")
public class OriginDataSourceConfig {
    @Bean(name = "originDatasource")
    @ConfigurationProperties(prefix = "spring.origin-datasource")
    public DataSource originDatasource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "originSqlSessionFactory")
    public SqlSessionFactory originSqlSessionFactory(@Qualifier("originDatasource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/origin/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "originSqlSessionTemplate")
    public SqlSessionTemplate originSqlSessionTemplate(@Qualifier("originSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
