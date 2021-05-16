package com.zjq.delayplugin;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zjq.util.AdvancedMqUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
  * @Description: 消息延时插件-生产者测试
  * @Author: zhangjunqiang
  * @Date: 2021/5/16 12:09
  * @version v1.0
  */
public class DelayPluginProducer {

    public static void main(String[] args) throws IOException, TimeoutException {
        // 建立连接
        Connection connection = AdvancedMqUtil.instanceConnection();
        // 创建信道
        Channel channel = AdvancedMqUtil.instanceChannel(connection);
        // 延时投递，10s
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND,10);
        Date delayTime = calendar.getTime();
        // 消息1
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String msg = "发送时间：" + sf.format(now) + "，投递时间：" + sf.format(delayTime);
        // 延时参数设置
        Map<String,Object> headers = new HashMap<>();
        headers.put("x-delay",delayTime.getTime() - now.getTime());
        AMQP.BasicProperties props = new AMQP.BasicProperties.Builder()
                .headers(headers)
                .contentEncoding("UTF-8").build();
        // 发送消息1
        channel.basicPublish("DELAY_EXCHANGE","DELAY_KEY",props,msg.getBytes(StandardCharsets.UTF_8));


        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(Calendar.SECOND,5);
        Date delayTime2 = calendar2.getTime();
        // 消息2
        String msg2 = "发送时间：" + sf.format(now) + "，投递时间：" + sf.format(delayTime2);
        // 延时参数设置
        Map<String,Object> headers2 = new HashMap<>();
        headers2.put("x-delay",delayTime2.getTime() - now.getTime());
        AMQP.BasicProperties props2 = new AMQP.BasicProperties.Builder()
                .headers(headers2)
                .contentEncoding("UTF-8").build();
        // 发送消息2
        channel.basicPublish("DELAY_EXCHANGE","DELAY_KEY",props2,msg2.getBytes(StandardCharsets.UTF_8));

        // 释放资源
        channel.close();
        connection.close();
    }

}
