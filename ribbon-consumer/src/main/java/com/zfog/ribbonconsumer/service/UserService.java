package com.zfog.ribbonconsumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import com.zfog.ribbonconsumer.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    private String url = "http://PROVIDER-USER/get/{1}";

    /**
     * 同步方式
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "userFallback")
    public User findUserById(Long id){
        return restTemplate.getForObject(url, User.class, id);
    }

   /* @HystrixCommand
    public findUserByIdAsync(Long id){
        AsyncResult<User> asyncResult = new AsyncResult<User>() {
            @Override
            public User invoke(){
                return restTemplate.getForObject(url, User.class, id);
            }
        };
        return asyncResult;
    }*/
}
