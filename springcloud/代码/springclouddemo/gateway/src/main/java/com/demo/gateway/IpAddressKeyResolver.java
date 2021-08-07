package com.demo.gateway;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
  * @Description: 通过ip地址进行限流
  * @Author: zhangjunqiang
  * @Date: 2021/8/7 18:14
  * @version v1.0
  */
@Component
public class IpAddressKeyResolver implements KeyResolver{

    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        return Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
    }
}
