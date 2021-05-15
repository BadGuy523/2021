package com.zjq.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

/**
  * @Description: rabbitMQ进阶工具类
  * @Author: zhangjunqiang
  * @Date: 2021/5/15 23:52
  * @version v1.0
  */
public class AdvancedMqUtil {

    // 连接工厂类
    private final static ConnectionFactory factory = new ConnectionFactory();

    static {
        try {
            factory.setUri(ResourceUtil.getKey("rabbitmq.uri"));
        } catch (URISyntaxException | NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建信道
     *
     * @param connection 连接
     * @return Channel
     * @throws IOException
     * @throws TimeoutException
     */
    public static Channel instanceChannel(Connection connection) throws IOException, TimeoutException {
        // 创建消息信道
        Channel channel = connection.createChannel();
        return channel;
    }

    /**
     * 创建连接
     *
     * @return Connection
     * @throws IOException
     * @throws TimeoutException
     */
    public static Connection instanceConnection() throws IOException, TimeoutException {
        return factory.newConnection();
    }

}
