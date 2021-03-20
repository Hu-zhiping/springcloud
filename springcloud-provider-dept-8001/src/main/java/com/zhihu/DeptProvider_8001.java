package com.zhihu;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * ribbon和eureka整合以后可以直接调用，不用关心ip地址和端口号的变化。
 */
@EnableDiscoveryClient // 服务发现
@EnableEurekaClient // 向注册中心注册自己
@SpringBootApplication
public class DeptProvider_8001 {
    public static void main(String[] args) {
        SpringApplication.run(DeptProvider_8001.class,args);
    }
    @Bean
    public ServletRegistrationBean hystrixMetricsStreamServlet(){
        ServletRegistrationBean servletRegistrationBean=new ServletRegistrationBean(new HystrixMetricsStreamServlet());
        return servletRegistrationBean;
    }
}
