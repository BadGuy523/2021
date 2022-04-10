package com.zjq.batchdemo.config;

import com.zjq.batchdemo.listener.StringJobListener;
import com.zjq.batchdemo.listener.StringStepListener;
import com.zjq.batchdemo.processor.StringProcessor;
import com.zjq.batchdemo.reader.StringReader;
import com.zjq.batchdemo.writer.StringWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
  * @Description: 配置类，注入Job、Step的Bean
  * @Author: zhangjunqiang
  * @Date: 2022/4/9 23:02
  * @version v1.0
  */
@Configuration
@EnableBatchProcessing
public class StringBatchConfig {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public ItemProcessor string2Processor;

    @Autowired
    public ItemReader string2Reader;

    @Autowired
    public ItemWriter string2Writer;

    @Bean
    public StepExecutionListener stringStepListener() {
        return new StringStepListener();
    }

    @Bean
    public JobExecutionListener stringJobListener() {
        return new StringJobListener();
    }

    @Bean
    public ItemProcessor stringProcessor() {
        return new StringProcessor();
    }

    @Bean
    public ItemReader stringReader() {
        return new StringReader();
    }

    @Bean
    public ItemWriter stringWriter() {
        return new StringWriter();
    }

    @Bean
    public Step stringStep(ItemReader stringReader, ItemProcessor stringProcessor, ItemWriter stringWriter, StepExecutionListener stringStepListener) {
        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return stepBuilderFactory.get(funcName)
                .listener(stringStepListener)
                .<String,String>chunk(3)
                .reader(stringReader)
                .processor(stringProcessor)
                .writer(stringWriter)
                .build();
    }

    @Bean
    public Step string2Step(StepExecutionListener stringStepListener) {
        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return stepBuilderFactory.get(funcName)
                .listener(stringStepListener)
                .<String,String>chunk(3)
                .reader(string2Reader)
                .processor(string2Processor)
                .writer(string2Writer)
                .build();
    }

    @Bean
    public Job stringJob(Step stringStep, JobExecutionListener stringJobListener,Step string2Step) {
        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return jobBuilderFactory.get(funcName)
                .listener(stringJobListener)
                .flow(stringStep)
                .next(string2Step) // next可以执行job的下一个step
                .end()
                .build();
    }
}
