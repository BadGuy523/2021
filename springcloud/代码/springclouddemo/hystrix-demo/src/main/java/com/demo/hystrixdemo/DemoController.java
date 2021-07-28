package com.demo.hystrixdemo;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DemoController {

    @Autowired
    RestTemplate restTemplate;

    @Bean
    public SimpleClientHttpRequestFactory httpRequestFactory() {
        SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
        httpRequestFactory.setReadTimeout(2000);
        httpRequestFactory.setConnectTimeout(2000);
        return httpRequestFactory;
    }

    @Bean
    public RestTemplate restTemplate(SimpleClientHttpRequestFactory httpRequestFactory) {
        return new RestTemplate(httpRequestFactory);
    }

    /**
     * 熔断触发降级测试（所有参数都在HystrixCommandProperties类中）
     * 如何触发熔断：10s之内，发起了5次请求，失败率超过50%；熔断时间5s恢复之前，请求都不会到达服务端
     *
     * @return
     */
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"), // 断路器是否开启
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"), // 最小请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000"), // 熔断的时间，该时间过后会恢复
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50") // 失败请求百分比
        },fallbackMethod = "fallback")
    @GetMapping("/test/{num}")
    public String test(@PathVariable Integer num) {
        if (num == 1) {
            return "success";
        }
        return restTemplate.getForEntity("http://localhost:8080/test",String.class).getBody();
    }

    public String fallback(Integer num) {
        return "系统繁忙";
    }

    /**
     * 请求超时触发降级测试（所有参数都在HystrixCommandProperties类中）
     *
     * @return
     */
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "4000") // 设置超时时间
        },fallbackMethod = "timeoutFallback")
    @GetMapping("/test2/{num}")
    public String test2(@PathVariable Integer num) {
        if (num == 1) {
            return "success";
        }
        return restTemplate.getForEntity("http://localhost:8080/test",String.class).getBody();
    }

    public String timeoutFallback(Integer num) {
        return "请求超时";
    }

}
