package com.demo;

import com.demo.api.DemoApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController implements DemoApi {

    @Value("${server.port}")
    private int port;

    @Override
    public String test() {
        return "访问端口："+port;
    }

}
