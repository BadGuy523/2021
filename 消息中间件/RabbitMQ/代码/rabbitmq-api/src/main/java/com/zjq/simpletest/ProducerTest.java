package com.zjq.simpletest;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zjq.util.SimpleMqUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * @Description: 消息生产者测试类
  * @Author: zhangjunqiang
  * @Date: 2021/5/9 18:11
  * @version v1.0
  */
public class ProducerTest {

    private final static Logger logger = LoggerFactory.getLogger(ProducerTest.class);

    // 交换机名称
    private final static String EXCHANGE_NAME = "SIMPLE_EXCHANGE";

    public static void main(String[] args) throws Exception {
        // 创建连接
        Connection connection = SimpleMqUtil.instanceConnection();
        // 创建消息信道
        Channel channel = SimpleMqUtil.instanceChannel(connection);
        // 消息内容
        String msg = "Hello world, Rabbit MQ";
        // 发布消息（参数含义：交换机名称、路由键、基础参数，消息体）
        channel.basicPublish(EXCHANGE_NAME, "direct.key", null, msg.getBytes());
        // 释放资源
        channel.close();
        connection.close();
    }
}
