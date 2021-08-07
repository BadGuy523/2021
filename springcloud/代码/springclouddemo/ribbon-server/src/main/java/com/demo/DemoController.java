package com.demo;

import com.demo.api.DemoApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;

// 用于刷新config服务端获取的配置，需要引入actuator,并配置文件中暴露refresh端点，手动调用http://localhost:8080/actuator/refresh才可刷新
@RefreshScope
@RestController
public class DemoController implements DemoApi {

    @Value("${server.port}")
    private int port;

//    @Value("${server.name}")
//    private String name;

    @Override
    public String test() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "name："+"name";
    }

    @Override
    public String test2() {
        return "port："+port;
    }

}
