package com.demo.client;

import com.demo.api.DemoApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("ribbon-server")
public interface DemoFeignClient extends DemoApi {

}
