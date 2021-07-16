package com.demo.api;

import org.springframework.web.bind.annotation.GetMapping;

public interface DemoApi {

    @GetMapping("/test")
    String test();

}
