package com.demo.springboot.dubbo.consumer.controller;

import com.demo.springboot.dubbo.api.ISayHelloService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
  * @Description: sayHello控制层
  * @Author: zhangjunqiang
  * @Date: 2021/8/14 16:42
  * @version v1.0
  */
@RestController
public class SayHelloController {

    // 指定调用的服务注册中心和版本号，多服务注册中心会采取相应的负载均衡策略
    @DubboReference(registry = {"shanghai","hunan"},version = "2.0")
    private ISayHelloService iSayHelloService;

    @GetMapping("/say")
    public String say() {
        return iSayHelloService.sayHello("man");
    }

}
