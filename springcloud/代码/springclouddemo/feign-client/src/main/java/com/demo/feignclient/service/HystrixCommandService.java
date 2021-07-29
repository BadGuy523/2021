package com.demo.feignclient.service;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.springframework.web.client.RestTemplate;

/**
  * @Description: 实现HystrixCommand实现降级
  * @Author: zhangjunqiang
  * @Date: 2021/7/29 23:38
  * @version v1.0
  */
public class HystrixCommandService extends HystrixCommand<String> {

    int num;

    RestTemplate restTemplate;

    public HystrixCommandService(int num,RestTemplate restTemplate) {
        super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ribbon-server"))
        .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
        .withCircuitBreakerEnabled(true)
        .withCircuitBreakerRequestVolumeThreshold(5)));
        this.num = num;
        this.restTemplate = restTemplate;
    }

    public HystrixCommandService(HystrixCommandGroupKey group) {
        super(group);
    }

    @Override
    protected String run() throws Exception {
        if (num % 2 == 0) {
            return "正常访问";
        }
        // 发起远程请求
        return restTemplate.getForObject("http://localhost:8080/test",String.class);
    }

    // 如果Hystrix触发了降级，那么会调用fallback方法
    @Override
    protected String getFallback() {
        return "请求被降级";
    }
}
