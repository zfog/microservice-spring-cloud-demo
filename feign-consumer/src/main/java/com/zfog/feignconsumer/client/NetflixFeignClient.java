package com.zfog.feignconsumer.client;

import com.zfog.feignconsumer.config.FeignConfig;
import com.zfog.feignconsumer.hystrix.HystrixFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "service-provider", configuration = FeignConfig.class,
        fallback = HystrixFallback.class)
public interface NetflixFeignClient {

    @GetMapping("/hello")
    String hello();
}
