package com.dubbotry;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@DubboComponentScan(basePackages = "com.dubbotry.service")
public class ProviderRunner {
    public static void main(String[] args) {
        SpringApplication.run(ProviderRunner.class,args);
    }
}
