package com.zfog.ribbonconsumer.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 配置RestTemplate
 */
@Configuration
public class RestConfig {

    /**
     * 注入RestTemplate
     * 并通过@LoadBalanced开启负载均衡（需要引入ribbon的依赖）
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
