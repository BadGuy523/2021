package com.zjq.batchdemo.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;

/**
  * @Description: Step的监听器
  * @Author: zhangjunqiang
  * @Date: 2022/4/9 23:04
  * @version v1.0
  */
@Slf4j
public class StringStepListener extends StepExecutionListenerSupport {
    public StringStepListener() {
        super();
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("after step : " + stepExecution.getStatus().toString());
        return super.afterStep(stepExecution);
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("before step");
        super.beforeStep(stepExecution);
    }
}
