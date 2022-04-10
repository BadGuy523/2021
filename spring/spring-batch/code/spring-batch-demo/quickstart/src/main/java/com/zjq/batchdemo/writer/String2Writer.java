package com.zjq.batchdemo.writer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
  * @Description: 写数据
  * @Author: zhangjunqiang
  * @Date: 2022/4/9 23:05
  * @version v1.0
  */
@Slf4j
@Component
public class String2Writer implements ItemWriter<String> {
    @Override
    public void write(List<? extends String> list) throws Exception {
        log.info("receive message :");
        for (String s : list) {
            log.info(s);
        }
    }
}
