package com.demo.ribbonclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RefreshScope
@RestController
public class DemoController {

    @Value("${client.name}")
    private String name;

//    @Autowired
//    private RestTemplate restTemplate;

//    @Bean
//    @LoadBalanced
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }

    @GetMapping("/test")
    public String test(){
        return "name:" + name;
//        return restTemplate.getForObject("http://ribbon-server/test",String.class);
    }

}
