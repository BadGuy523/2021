package com.zjq.simpletest;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zjq.util.SimpleMqUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
  * @Description: 删除交换机和队列测试类
  * @Author: zhangjunqiang
  * @Date: 2021/5/9 19:22
  * @version v1.0
  */
public class DeleteTest {

    // 交换机名称
    private final static String EXCHANGE_NAME = "SIMPLE_EXCHANGE";

    // 队列名称
    private final static String QUEUE_NAME = "SIMPLE_QUEUE";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接
        Connection connection = SimpleMqUtil.instanceConnection();
        // 创建消息信道
        Channel channel = SimpleMqUtil.instanceChannel(connection);
        // 删除队列
        channel.queueDelete(QUEUE_NAME);
        // 删除交换机
        channel.exchangeDelete(EXCHANGE_NAME);
        // 释放资源
        channel.close();
        connection.close();
    }
}
