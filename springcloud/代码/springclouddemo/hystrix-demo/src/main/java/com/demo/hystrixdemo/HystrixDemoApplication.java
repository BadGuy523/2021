package com.demo.hystrixdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@EnableCircuitBreaker
@SpringBootApplication
public class HystrixDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDemoApplication.class,args);
    }
}
