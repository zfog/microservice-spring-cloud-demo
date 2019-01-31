package com.zfog.ribbonconsumer.controller;

import com.zfog.ribbonconsumer.pojo.User;
import com.zfog.ribbonconsumer.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;
    private String url = "http://SERVICE-PROVIDER/hello";
    @Autowired
    private HelloService helloService;

    @GetMapping("/ribbon-consumer")
    public String helloConsumer() throws InterruptedException {
        String returnMsg = helloService.helloService();
        if(!"error".equals(returnMsg)) {
            return returnMsg;
        }else{
            System.out.println(returnMsg);
            return returnMsg;
        }
    }

    @GetMapping("/hello")
    public void hello() {
        System.out.println("hello-ribbon");
        restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/order/{id}")
    public User getOrder(@PathVariable Long id) {
        System.out.println(url + id + "  " + 123456);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        String body = responseEntity.getBody();
        User userObject = restTemplate.getForObject(url + id, User.class);
        return userObject;
    }

    @GetMapping("/order-test/{msg}")
    public String sendMsg(@PathVariable String msg) {
        System.out.println(msg);
        return msg;
    }
}
