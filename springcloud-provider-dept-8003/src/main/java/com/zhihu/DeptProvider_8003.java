package com.zhihu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * ribbon和eureka整合以后可以直接调用，不用关心ip地址和端口号的变化。
 */
@EnableDiscoveryClient // 服务发现
@EnableEurekaClient // 向注册中心注册自己
@SpringBootApplication
public class DeptProvider_8003 {
    public static void main(String[] args) {
        SpringApplication.run(DeptProvider_8003.class,args);
    }
}
