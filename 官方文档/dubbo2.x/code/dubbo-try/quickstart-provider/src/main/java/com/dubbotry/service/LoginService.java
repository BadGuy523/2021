package com.dubbotry.service;

import com.dubbotry.api.LoginApi;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class LoginService implements LoginApi {
    @Override
    public String login(String username) {
        return "hello," + username;
    }
}
