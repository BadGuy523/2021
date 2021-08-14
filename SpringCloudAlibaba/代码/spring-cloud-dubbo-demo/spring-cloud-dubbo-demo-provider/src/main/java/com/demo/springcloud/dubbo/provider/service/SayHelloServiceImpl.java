package com.demo.springcloud.dubbo.provider.service;

import com.demo.springcloud.dubbo.api.ISayHelloService;
import org.apache.dubbo.config.annotation.Service;

/**
  * @Description: dayHello接口实现
  * @Author: zhangjunqiang
  * @Date: 2021/8/14 16:36
  * @version v1.0
  */
@Service
public class SayHelloServiceImpl implements ISayHelloService {
    @Override
    public String sayHello(String msg) {
        return "Yo!Hello " + msg;
    }
}
