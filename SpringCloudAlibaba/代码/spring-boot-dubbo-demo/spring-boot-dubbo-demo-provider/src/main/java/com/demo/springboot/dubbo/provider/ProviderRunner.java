package com.demo.springboot.dubbo.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 配置文件已经指定扫描路径
// @DubboComponentScan(basePackages = "com.demo.springboot.dubbo.provider.service")
@SpringBootApplication
public class ProviderRunner {
    public static void main(String[] args) {
        SpringApplication.run(ProviderRunner.class,args);
    }
}
