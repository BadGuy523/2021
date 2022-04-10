package com.zjq.batchdemo.config;

import com.zjq.batchdemo.model.User;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
  * @Description: 数据库到数据库配置类
  * @Author: zhangjunqiang
  * @Date: 2022/4/10 21:48
  * @version v1.0
  */
@Configuration
@EnableBatchProcessing
public class DbBatchConfig {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    @Qualifier("originSqlSessionFactory")
    public SqlSessionFactory originSqlSessionFactory;

    @Autowired
    @Qualifier("targetSqlSessionFactory")
    public SqlSessionFactory targetSqlSessionFactory;

    @Autowired
    public SkipListener userSkipListener;

    @Bean
    public ItemReader<User> mybatisReader() {
        MyBatisCursorItemReader<User> reader = new MyBatisCursorItemReader<>();
        reader.setSqlSessionFactory(originSqlSessionFactory);
        reader.setQueryId("com.zjq.batchdemo.dao.origin.OriginDao.queryUserList");
        return reader;
    }

    @Bean
    public ItemWriter<User> mybatisWriter() {
        MyBatisBatchItemWriter<User> writer = new MyBatisBatchItemWriter<>();
        writer.setSqlSessionFactory(targetSqlSessionFactory);
        writer.setStatementId("com.zjq.batchdemo.dao.targets.TargetsDao.insertUser");
        return writer;
    }



    @Bean
    public Step dbStep(ItemReader mybatisReader, ItemWriter mybatisWriter, StepExecutionListener stringStepListener) {
        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return stepBuilderFactory.get(funcName)
                .listener(stringStepListener)
                .<User,User>chunk(10)
                .reader(mybatisReader)
                .writer(mybatisWriter)
                .faultTolerant()
                .skipLimit(1000)
                .skip(Exception.class)
                .listener(userSkipListener)
//                .retryLimit(3)
//                .retry(Exception.class)
                .build();
    }

    @Bean
    public Job dbJob(Step dbStep, JobExecutionListener stringJobListener) {
        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return jobBuilderFactory.get(funcName)
                .listener(stringJobListener)
                .flow(dbStep)
                .end()
                .build();
    }
}
