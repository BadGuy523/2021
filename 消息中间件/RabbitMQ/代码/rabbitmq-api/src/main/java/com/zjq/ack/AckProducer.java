package com.zjq.ack;

import com.zjq.util.ResourceUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
  * @Description: 消息生产者，用于测试消费者手工应答和重回队列
  * @Author: zhangjunqiang
  * @Date: 2021/5/16 13:24
  * @version v1.0
  */
public class AckProducer {
    private final static String QUEUE_NAME = "TEST_ACK_QUEUE";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(ResourceUtil.getKey("rabbitmq.uri"));

        // 建立连接
        Connection conn = factory.newConnection();
        // 创建消息通道
        Channel channel = conn.createChannel();

        String msg = "test ack message ";
        // 声明队列（默认交换机AMQP default，Direct）
        // String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 发送消息
        // String exchange, String routingKey, BasicProperties props, byte[] body
        for (int i =0; i<5; i++){
            String str = msg + i;
            if (i % 2 == 0)
                str = str + "异常";
            byte[] bytes = str.getBytes();
            channel.basicPublish("", QUEUE_NAME, null, bytes);


        }

        channel.close();
        conn.close();
    }
}

