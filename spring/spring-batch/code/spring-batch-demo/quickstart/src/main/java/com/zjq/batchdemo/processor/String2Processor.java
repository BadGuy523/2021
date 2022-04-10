package com.zjq.batchdemo.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
  * @Description: 数据处理2
  * @Author: zhangjunqiang
  * @Date: 2022/4/9 23:04
  * @version v1.0
  */
@Slf4j
@Component
public class String2Processor implements ItemProcessor<String,String> {
    @Override
    public String process(String s) throws Exception {
        log.info("convert " + s + " to : " + s.substring(0,2));
        return s.substring(0,2);
    }
}
