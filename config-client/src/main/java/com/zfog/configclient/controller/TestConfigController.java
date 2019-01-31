package com.zfog.configclient.controller;

import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RefreshScope
@RestController
public class TestConfigController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${zfog.from}")
    String from;

    @GetMapping("/config/test")
    public String testConfig(){
        return from;
    }

    @GetMapping("/post")
    public void postReq(){
        restTemplate.postForLocation("http://localhost:7003/bus/refresh", null);
    }
}
