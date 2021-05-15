package com.zjq.deadletter;

import com.rabbitmq.client.*;
import com.zjq.util.AdvancedMqUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
  * @Description: 死信测试-消费者，由于消费的代码注释了，10秒后，消息会从正常队列到达死信交换机，再路由到死信队列
  * @Author: zhangjunqiang
  * @Date: 2021/5/16 0:07
  * @version v1.0
  */
public class DlxConsumerTest {

    public static void main(String[] args) throws IOException, TimeoutException {
        // 建立连接
        Connection connection = AdvancedMqUtil.instanceConnection();
        // 创建信道
        Channel channel = AdvancedMqUtil.instanceChannel(connection);
        // 指定队列的死信交换机
        Map<String,Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange","GP_DEAD_LETTER_EXCHANGE");
        // 设置队列的TTL
        // arguments.put("x-expires",9000L);
        // 如果设置了队列的最大长度，超过长度时，先入队的消息会被发送到DLX
        // arguments.put("x-max-length", 4);

        // 声明队列
        // String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
        channel.queueDeclare("GP_ORI_USE_QUEUE", false, false, false, arguments);

        // 声明死信交换机
        channel.exchangeDeclare("GP_DEAD_LETTER_EXCHANGE","topic", false, false, false, null);
        // 声明死信队列
        channel.queueDeclare("GP_DEAD_LETTER_QUEUE", false, false, false, null);
        // 绑定，此处 Dead letter routing key 设置为 #
        channel.queueBind("GP_DEAD_LETTER_QUEUE","GP_DEAD_LETTER_EXCHANGE","#");
        System.out.println(" Waiting for message....");

//        // 创建消费者（消费队列数据）
//        Consumer consumer = new DefaultConsumer(channel) {
//            @Override
//            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
//                                       byte[] body) throws IOException {
//                String msg = new String(body, "UTF-8");
//                System.out.println("Received message by normal queue: '" + msg + "'");
//            }
//        };
//
//        // 开始获取消息
//        // String queue, boolean autoAck, Consumer callback
//        channel.basicConsume("GP_ORI_USE_QUEUE", true, consumer);

        // 创建消费者（消费死信队列数据）
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                String msg = new String(body, "UTF-8");
                System.out.println("Received message by dlq: '" + msg + "'");
            }
        };

        // 开始获取消息
        // String queue, boolean autoAck, Consumer callback
        channel.basicConsume("GP_DEAD_LETTER_QUEUE", true, consumer);
    }

}
