package com.zfog.feignconsumer.hystrix;

import com.zfog.feignconsumer.client.NetflixFeignClient;
import org.springframework.stereotype.Component;

@Component
public class HystrixFallback implements NetflixFeignClient {
    @Override
    public String hello() {
        return "error";
    }
}
