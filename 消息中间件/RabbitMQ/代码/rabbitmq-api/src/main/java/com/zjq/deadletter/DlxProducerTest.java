package com.zjq.deadletter;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zjq.util.AdvancedMqUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
  * @Description: 死信测试-生产者,通过消息TTL（）来测试
  * @Author: zhangjunqiang
  * @Date: 2021/5/15 23:45
  * @version v1.0
  */
public class DlxProducerTest {

    public static void main(String[] args) throws IOException, TimeoutException {
        // 建立连接
        Connection connection = AdvancedMqUtil.instanceConnection();
        // 创建信道
        Channel channel = AdvancedMqUtil.instanceChannel(connection);
        // 消息内容
        String msg = "Nice to meet you,DLX";
        // 设置消息属性
        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                .deliveryMode(2) // 持久化消息
                .contentEncoding("UTF-8")
                .expiration("10000") // TTL
                .build();
        // 发送消息
        channel.basicPublish("","GP_ORI_USE_QUEUE",properties,msg.getBytes());
        // 释放资源
        channel.close();
        connection.close();
    }

}
