package com.demo.feignclient;

import com.demo.client.DemoFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoOpenFeignController {

//    @Autowired
//    private MyFeignClient myFeignClient;

    @Autowired
    private DemoFeignClient demoFeignClient;

    @GetMapping("/test2")
    public String test2() {
//        return myFeignClient.test();
        return demoFeignClient.test2();
    }

    @GetMapping("/test")
    public String test() {
        return demoFeignClient.test();
    }

}
