package com.zjq.service.impl;

import com.zjq.service.ILoginService;

/**
 * @description: 登录实现类
 * @author: BadGuy
 * @date: 2022-02-19 11:54
 **/
public class LoginServiceImpl implements ILoginService {
    @Override
    public String login(String username, String password) {
        return "success: " + username;
    }
}
