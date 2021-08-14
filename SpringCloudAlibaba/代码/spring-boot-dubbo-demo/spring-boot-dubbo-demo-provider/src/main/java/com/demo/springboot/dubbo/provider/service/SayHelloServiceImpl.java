package com.demo.springboot.dubbo.provider.service;

import com.demo.springboot.dubbo.api.ISayHelloService;
import org.apache.dubbo.config.annotation.DubboService;

/**
  * @Description: dayHello接口实现
  * @Author: zhangjunqiang
  * @Date: 2021/8/14 16:36
  * @version v1.0
  */
// 指定要注册的服务注册中心及版本号
@DubboService(registry = {"shanghai","hunan"},version = "1.0")
public class SayHelloServiceImpl implements ISayHelloService {
    @Override
    public String sayHello(String msg) {
        return "[v1.0]Yo!Hello " + msg;
    }
}
