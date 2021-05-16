package com.zjq.queuettl;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zjq.util.AdvancedMqUtil;

import java.util.HashMap;
import java.util.Map;

/**
  * @Description: 消息生产者，队列TTL测试
  * @Author: zhangjunqiang
  * @Date: 2021/5/16 13:06
  * @version v1.0
  */
public class TTLProducer {
    public static void main(String[] args) throws Exception {
        // 建立连接
        Connection connection = AdvancedMqUtil.instanceConnection();
        // 创建信道
        Channel channel = AdvancedMqUtil.instanceChannel(connection);

        String msg = "Hello world, Rabbit MQ, DLX MSG";

        // 通过队列属性设置消息过期时间
        Map<String, Object> argss = new HashMap<String, Object>();
        argss.put("x-message-ttl",6000);
        // 如果之前已经创建了没有死信交换机参数的队列，则需要将原来队列删除后重新创建。
        argss.put("x-dead-letter-exchange","GP_DEAD_LETTER_EXCHANGE");
        // 声明队列（默认交换机AMQP default，Direct）
        // String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
        channel.queueDeclare("TEST_TTL_QUEUE", false, false, false, argss);

        // 对每条消息设置过期时间
        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                .deliveryMode(2) // 持久化消息
                .contentEncoding("UTF-8")
                .expiration("10000") // TTL
                .build();

        // 此处两种方式设置消息过期时间的方式都使用了，将以较小的数值为准

        // 发送消息
        channel.basicPublish("", "TEST_TTL_QUEUE", properties, msg.getBytes());

        channel.close();
        connection.close();
    }
}
