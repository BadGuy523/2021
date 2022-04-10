package com.zjq.batchdemo.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

/**
  * @Description: Job的监听器
  * @Author: zhangjunqiang
  * @Date: 2022/4/9 23:03
  * @version v1.0
  */
@Slf4j
public class StringJobListener extends JobExecutionListenerSupport {
    public StringJobListener() {
        super();
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("after job");
        super.afterJob(jobExecution);
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("before job");
        super.beforeJob(jobExecution);
    }
}
