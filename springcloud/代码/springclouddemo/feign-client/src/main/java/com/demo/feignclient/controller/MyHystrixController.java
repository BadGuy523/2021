package com.demo.feignclient.controller;

import com.demo.feignclient.myhystrix.MyHystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MyHystrixController {

    @Autowired
    RestTemplate restTemplate;

    @MyHystrixCommand(fallback = "fallback",timeout = 3000)
    @GetMapping("/hystrix/test")
    public String test() {
        return restTemplate.getForObject("http://localhost:8080/test",String.class);
    }

    public String fallback() {
        return "请求被降级，fallback";
    }

}
