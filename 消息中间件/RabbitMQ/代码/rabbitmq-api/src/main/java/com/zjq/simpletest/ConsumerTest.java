package com.zjq.simpletest;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.zjq.util.SimpleMqUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
  * @Description: 消息消费者测试类
  * @Author: zhangjunqiang
  * @Date: 2021/5/9 17:35
  * @version v1.0
  */
public class ConsumerTest {

    private final static Logger logger = LoggerFactory.getLogger(ConsumerTest.class);

    // 交换机名称
    private final static String EXCHANGE_NAME = "SIMPLE_EXCHANGE";

    // 队列名称
    private final static String QUEUE_NAME = "SIMPLE_QUEUE";

    // 队列名称2
    private final static String QUEUE_NAME2 = "SIMPLE_QUEUE2";

    /**
     * 创建了两个队列，测试对于直连交换机，绑定键相同时，一条消息是否可以路由到两个队列；结论：可以
     *
     * @param args
     * @throws IOException
     * @throws TimeoutException
     */
    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接
        Connection connection = SimpleMqUtil.instanceConnection();
        // 创建消息信道
        Channel channel = SimpleMqUtil.instanceChannel(connection);
        // 声明交换机(参数含义：交换机名称、交换机类型、是否持久化、是否自动删除、map参数)
        channel.exchangeDeclare(EXCHANGE_NAME,"direct",true,false,null);
        // 声明队列(参数含义：队列名称、是否持久化、是否独占、是否自动删除、map参数)
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        // 声明队列2(参数含义：队列名称、是否持久化、是否独占、是否自动删除、map参数)
        channel.queueDeclare(QUEUE_NAME2,true,false,false,null);
        logger.info("waiting for message ...");
        // 绑定队列和交换机（参数含义：队列名称、交换机名称、绑定键）
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"direct.key");
        // 绑定队列和交换机2（参数含义：队列名称、交换机名称、绑定键）
        channel.queueBind(QUEUE_NAME2,EXCHANGE_NAME,"direct.key");
        // 创建消费者
        Consumer consumer = new MyConsumer(channel);
        // 开始获取消息（push方式）(参数含义：队列名称、是否自动确认消息接受，回调)
        channel.basicConsume(QUEUE_NAME,true,consumer);

        // 创建消费者2
        Consumer consumer2 = new MyConsumer(channel);
        // 开始获取消息（push方式）(参数含义：队列名称、是否自动确认消息接受，回调)
        channel.basicConsume(QUEUE_NAME2,true,consumer2);
    }
}
