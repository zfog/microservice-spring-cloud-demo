package com.zfog.feignconsumer.controller;

import com.zfog.feignconsumer.service.FeignHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignConsumerController {

    @Autowired
    private FeignHelloService feignHelloService;
    @Value("${server.port}")
    private Integer port;

    @GetMapping("/feign-consumer")
    public String consumerHello(){
        return feignHelloService.hello();
    }

    @GetMapping("/hello")
    public String hello(){
        return "i'm feign-consumer from port:" + port;
    }

}
