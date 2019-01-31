package com.zfog.serviceprovider.controller;

import com.zfog.serviceprovider.pojo.User;
import org.apache.http.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Value("${server.port}")
    private String port;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id) {
        return new User(id);
    }

    @GetMapping("/user/test/{msg}")
    public String sendMsg(@PathVariable String msg) {
        return msg;
    }

    @GetMapping("/hello")
    public String hello(HttpServletRequest request) {
        System.out.println("hello, i'm from port:" + port);
        logger.info("========call service-provider, TraceId:{}, SpanId:{}=====>",
                request.getHeader("X-B3-TraceId"), request.getHeader("X-B3-SpanId"));
        return "hello, i'm from port:" + port;
    }
}
