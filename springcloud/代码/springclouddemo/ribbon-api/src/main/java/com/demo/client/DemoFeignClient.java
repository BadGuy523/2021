package com.demo.client;

import com.demo.api.DemoApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@FeignClient(value = "ribbon-server", fallback = DemoFeignClient.DemoFallback.class)
public interface DemoFeignClient extends DemoApi {

    @Component
    class DemoFallback implements DemoFeignClient {

        @Override
        public String test() {
            return "hystrix gotcha";
        }

        @Override
        public String test2() {
            return "hystrix gotcha";
        }
    }

}
