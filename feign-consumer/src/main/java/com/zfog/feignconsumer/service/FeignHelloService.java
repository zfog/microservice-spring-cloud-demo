package com.zfog.feignconsumer.service;

import com.zfog.feignconsumer.client.NetflixFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeignHelloService {

    @Autowired
    private NetflixFeignClient netflixFeignClient;

    public String hello() {
        return netflixFeignClient.hello();
    }
}
