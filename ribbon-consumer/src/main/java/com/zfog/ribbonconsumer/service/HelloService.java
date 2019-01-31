package com.zfog.ribbonconsumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.zfog.ribbonconsumer.pojo.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

@Service
public class HelloService {

    @Autowired
    private RestTemplate restTemplate;
    private String url = "http://SERVICE-PROVIDER/hello";

    private static final Random random = new Random();

    /**
     * 熔断器
     * 使用@HystrixCommand注解创建HystrixCommand实现，并通过fallbackMethod指定服务降级的实现
     *
     * @return
     */

    @HystrixCommand(fallbackMethod = "helloFallback",//设置fallback方法
            commandProperties = {// command 配置
                    //设置超时时间 100 ms
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100")
            })
    public String helloService() throws InterruptedException {
        System.out.println("helloService");
        long executionTime = random.nextInt(200);
        System.out.println("execution time : " + executionTime + "ms");
        Thread.sleep(executionTime);
        return restTemplate.getForObject(url, String.class);
    }

    /**
     * 降级方法
     * @return
     */
    public String helloFallback() {
        return "error";
    }

    /**
     * hystrix请求缓存
     * 将请求结果置于缓存中，缓存key为 user.id
     *
     * @param user
     * @return
     */
    @CacheResult
    @HystrixCommand
    public User getUserById(@CacheKey("id") User user) {
        return restTemplate.getForObject(url, User.class);
    }

    /**
     * 清除缓存
     * commandKey必须指定，此属性指定的是请求缓存的请求命令，以便在清除之后重新请求缓存
     *
     * @param user
     */
    @CacheRemove(commandKey = "getUserById")
    @HystrixCommand
    public void updateUser(@CacheKey("id") User user) {
        restTemplate.postForObject("http://SERVICE-PROVIDER/update", user, User.class);
    }

    /**
     * 请求合并
     * 将短时间内多个获取单一User的请求合并
     * 合并后的批量处理方法为 batchMethod所指定的方法，即findAll
     * timerDelayInMilliseconds设置延迟时间，合并器会在该时间内将收集单个User的请求合并
     * @param id
     * @return
     */
    @HystrixCollapser(batchMethod = "findAll",
            collapserProperties = {@HystrixProperty(name = "timerDelayInMilliseconds", value = "100")})
    public User findUserById(Long id) {
        return restTemplate.getForObject("http://SERVICE-PROVIDER/users/{1}", User.class, id);
    }

    @HystrixCommand
    public List<User> findAll(List<Long> ids) {
        return restTemplate.getForObject("http:SERVICE-PROVIDER/users?ids={1}", List.class, StringUtils.join(ids, ","));
    }
}
