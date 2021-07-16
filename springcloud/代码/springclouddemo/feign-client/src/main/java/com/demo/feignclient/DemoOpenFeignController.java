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

//    @GetMapping("/test")
//    public String test() {
//        return myFeignClient.test();
//    }

    @GetMapping("/test")
    public String test() {
        return demoFeignClient.test();
    }

}
