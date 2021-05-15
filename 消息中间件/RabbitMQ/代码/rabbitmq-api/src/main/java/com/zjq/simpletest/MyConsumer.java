package com.zjq.simpletest;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
  * @Description: 继承DefaultConsumer，重写相应方法
  * @Author: zhangjunqiang
  * @Date: 2021/5/9 18:03
  * @version v1.0
  */
public class MyConsumer extends DefaultConsumer {

    private final static Logger logger = LoggerFactory.getLogger(MyConsumer.class);

    public MyConsumer(Channel channel) {
        super(channel);
    }

    /**
     * 消费消息
     *
     * @param consumerTag
     * @param envelope
     * @param properties
     * @param body 消息体
     * @throws IOException
     */
    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        super.handleDelivery(consumerTag, envelope, properties, body);
        String msg = new String(body, "UTF-8");
        logger.info("Received message : '" + msg + "'");
        logger.info("consumerTag : " + consumerTag );
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("deliveryTag : " + envelope.getDeliveryTag() );
    }
}
