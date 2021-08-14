package com.demo.springcloud.dubbo.consumer.controller;

import com.demo.springcloud.dubbo.api.ISayHelloService;
import org.apache.dubbo.config.annotation.Reference;
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

    @Reference
    private ISayHelloService iSayHelloService;

    @GetMapping("/say")
    public String say() {
        return iSayHelloService.sayHello("man");
    }

}
