package com.demo.feignclient.myhystrix;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyHystrixCommand {

    // 默认超时时间
    int timeout() default 1000;

    // 回退方法名
    String fallback() default "";
}
