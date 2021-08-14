package com.demo.springcloud.dubbo.provider;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//@DubboComponentScan(basePackages = "com.demo.springcloud.dubbo.provider.service")报错
@EnableDiscoveryClient // 可加可不加，默认开启
@SpringBootApplication
public class ProviderRunner {
    public static void main(String[] args) {
        SpringApplication.run(ProviderRunner.class,args);
    }
}
