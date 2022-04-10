package com.zjq.batchdemo.reader;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

/**
  * @Description: 数据读取2
  * @Author: zhangjunqiang
  * @Date: 2022/4/9 23:04
  * @version v1.0
  */
@Slf4j
@Component
public class String2Reader implements ItemReader<String> {
    private String[] messages = {"alice","tom","jack"};

    private int count;

    /**
     * 返回null表示读取结束
     *
     * @return
     * @throws UnexpectedInputException
     * @throws ParseException
     * @throws NonTransientResourceException
     */
    @Override
    public String read() throws UnexpectedInputException, ParseException, NonTransientResourceException {
        if (count < messages.length) {
            log.info("read message : " + messages[count]);
            return messages[count++];
        } else {
            log.info("read message done");
            count = 0;
        }
        return null;
    }
}
