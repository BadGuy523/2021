package com.demo.feignclient.controller;

import com.demo.feignclient.service.HystrixCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HystrixCommandController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("hystrix/command/{num}")
    public String hystrixCommand(@PathVariable("num") int num) {
        HystrixCommandService hystrixCommandService = new HystrixCommandService(num,restTemplate);
        return hystrixCommandService.execute(); // 执行
    }

}
