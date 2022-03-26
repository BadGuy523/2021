package com.dubbotry.controller;

import com.dubbotry.api.LoginApi;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    // 不用配置中心需要指明url
    // @DubboReference(url = "dubbo://localhost:20880")
    // 引用dubbo服务
    @DubboReference
    private LoginApi loginApi;

    @RequestMapping("/login")
    public String login() {
        return loginApi.login("tom");
    }
}
