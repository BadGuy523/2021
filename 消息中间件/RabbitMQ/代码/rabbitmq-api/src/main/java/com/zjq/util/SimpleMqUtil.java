package com.zjq.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.impl.nio.NioParams;
import com.zjq.common.MqConstants;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
  * @Description: rabbitmq简单工具类
  * @Author: zhangjunqiang
  * @Date: 2021/5/9 19:43
  * @version v1.0
  */
public class SimpleMqUtil {

    // 连接工厂类
    private final static ConnectionFactory factory = new ConnectionFactory();

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
        // 设置连接ip
        factory.setHost(MqConstants.LOCAL_IP);
        // 设置监听端口
        factory.setPort(MqConstants.DEFAULT_LISTEN_PORT);
        // 设置vhost
        factory.setVirtualHost(MqConstants.VIRTUAL_HOST);
        // 设置用户名和密码
        factory.setUsername(MqConstants.USER_NAME);
        factory.setPassword(MqConstants.PASS_WORD);
        // 指定NIO线程数
        factory.setNioParams(new NioParams().setNbIoThreads(4));
        factory.useNio();
        // 建立连接
        Connection connection = factory.newConnection();
        return connection;
    }


}
