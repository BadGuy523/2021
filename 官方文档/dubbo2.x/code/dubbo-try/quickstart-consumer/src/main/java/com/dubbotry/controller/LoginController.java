package com.dubbotry.controller;

import com.dubbotry.api.LoginApi;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @DubboReference
    private LoginApi loginApi;

    @RequestMapping("/login")
    public String login() {
        return loginApi.login("tom");
    }
}
