package com.zjq.batchdemo.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

/**
  * @Description: 数据处理
  * @Author: zhangjunqiang
  * @Date: 2022/4/9 23:04
  * @version v1.0
  */
@Slf4j
public class StringProcessor implements ItemProcessor<String,String> {
    @Override
    public String process(String s) throws Exception {
        log.info("convert " + s + " to : " + s.toUpperCase());
        return s.toUpperCase();
    }
}
