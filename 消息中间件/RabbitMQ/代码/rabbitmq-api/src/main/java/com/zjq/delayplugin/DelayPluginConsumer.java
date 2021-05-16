package com.zjq.delayplugin;

import com.rabbitmq.client.*;
import com.zjq.util.AdvancedMqUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
  * @Description: 消息延时插件消费者测试
  * @Author: zhangjunqiang
  * @Date: 2021/5/16 12:01
  * @version v1.0
  */
public class DelayPluginConsumer {

    public static void main(String[] args) throws IOException, TimeoutException {
        // 建立连接
        Connection connection = AdvancedMqUtil.instanceConnection();
        // 创建信道
        Channel channel = AdvancedMqUtil.instanceChannel(connection);
        // 声明x-delayed-message类型的exchange
        Map<String,Object> param = new HashMap<>();
        param.put("x-delayed-type","direct");
        channel.exchangeDeclare("DELAY_EXCHANGE","x-delayed-message",false,false,param);
        // 声明队列
        channel.queueDeclare("DELAY_QUEUE",false,false,false,null);
        // 绑定交换机和队列
        channel.queueBind("DELAY_QUEUE","DELAY_EXCHANGE","DELAY_KEY");
        // 创建消费者
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                String msg = new String(body, "UTF-8");
                SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                System.out.println("收到消息：[" + msg + "]\n接收时间：" +sf.format(new Date()));
            }
        };
        // 开始获取消息
        channel.basicConsume("DELAY_QUEUE",true,consumer);
    }

}
