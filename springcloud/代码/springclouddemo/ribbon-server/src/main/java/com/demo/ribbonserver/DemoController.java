package com.demo.ribbonserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Value("${server.port}")
    private int port;

    @GetMapping("/test")
    public String test(){
        return "访问端口："+port;
    }

}
