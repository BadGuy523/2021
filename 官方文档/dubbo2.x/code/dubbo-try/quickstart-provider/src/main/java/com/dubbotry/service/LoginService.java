package com.dubbotry.service;

import com.dubbotry.api.LoginApi;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.concurrent.TimeUnit;

@Slf4j
// 声明这是一个dubbo服务
@DubboService
public class LoginService implements LoginApi {
    @Override
    public String login(String username) {
        // 睡眠2秒，客户端设置的超时时间为1s
        try {
            log.info("start sleep");
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello," + username;
    }
}
