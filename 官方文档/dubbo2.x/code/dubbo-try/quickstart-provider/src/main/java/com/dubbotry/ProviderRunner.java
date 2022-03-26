package com.dubbotry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 配置文件和注解配置一个就行
// @DubboComponentScan(basePackages = "com.dubbotry.service")
public class ProviderRunner {
    public static void main(String[] args) {
        SpringApplication.run(ProviderRunner.class,args);
    }
}
