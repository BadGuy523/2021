package com.zjq.returnlistener;

import com.rabbitmq.client.*;
import com.zjq.util.ResourceUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
  * @Description: 当消息无法匹配到队列时，会发回给生产者
  * @Author: zhangjunqiang
  * @Date: 2021/5/16 14:21
  * @version v1.0
  */
public class ReturnListenerProducer {
    public static void main(String[] args) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(ResourceUtil.getKey("rabbitmq.uri"));

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        // 没有设置备份交换机时，会触发回调，有备份交换机时（且备份交换机有绑定的队列），会被路由到备份交换机->再路由到对应队列，不触发回调
        channel.addReturnListener(new ReturnListener() {
            public void handleReturn(int replyCode,
                                     String replyText,
                                     String exchange,
                                     String routingKey,
                                     AMQP.BasicProperties properties,
                                     byte[] body)
                    throws IOException {
                System.out.println("=========监听器收到了无法路由，被返回的消息============");
                System.out.println("replyText:"+replyText);
                System.out.println("exchange:"+exchange);
                System.out.println("routingKey:"+routingKey);
                System.out.println("message:"+new String(body));
            }
        });

        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder().deliveryMode(2).
                contentEncoding("UTF-8").build();

//        // 备份交换机
        channel.exchangeDeclare("ALTERNATE_EXCHANGE","topic", false, false, false, null);
        channel.queueDeclare("ALTERNATE_QUEUE", false, false, false, null);
        channel.queueBind("ALTERNATE_QUEUE","ALTERNATE_EXCHANGE","#");

        // 在声明交换机的时候指定备份交换机
        Map<String,Object> arguments = new HashMap<String,Object>();
        arguments.put("alternate-exchange","ALTERNATE_EXCHANGE");
        channel.exchangeDeclare("TEST_EXCHANGE","topic", false, false, false, arguments);


        // 发送到了默认的交换机上，由于没有任何队列使用这个关键字跟交换机绑定，所以会被退回
        // 第三个参数是设置的mandatory，如果mandatory是false，消息也会被直接丢弃
        /**
         * 在RabbitMQ3.0以后的版本里去掉了immediate参数的支持，发送带immediate=true的publish会返回如下错误，
         * com.rabbitmq.client.AlreadyClosedException: connection is already closed due to connection error; protocol method: #method<connection.close>(reply-code=540, reply-text=NOT_IMPLEMENTED - immediate=true, class-id=60, method-id=40)
         * 为什么会取消immediate参数支持，immediate标记会影响镜像队列性能，增加代码复杂性，并建议采用TTL和DLX等方式代替
         */
        channel.basicPublish("TEST_EXCHANGE","qingshan2673",true, properties,"只为更好的你".getBytes());

        TimeUnit.SECONDS.sleep(10);

        channel.close();
        connection.close();
    }
}
