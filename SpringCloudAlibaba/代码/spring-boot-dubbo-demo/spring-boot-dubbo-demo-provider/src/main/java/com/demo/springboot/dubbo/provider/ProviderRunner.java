package com.demo.springboot.dubbo.provider;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@DubboComponentScan(basePackages = "com.demo.springboot.dubbo.provider.service") // 可行
@SpringBootApplication
public class ProviderRunner {
    public static void main(String[] args) {
        SpringApplication.run(ProviderRunner.class,args);
    }
}
