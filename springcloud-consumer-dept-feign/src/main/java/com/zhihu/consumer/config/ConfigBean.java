package com.zhihu.consumer.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 注册RestTemplate
 */
@Configuration
public class ConfigBean { // applicationContext.xml
    @Bean
    @LoadBalanced // 配置Ribbon负载均衡实现
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
