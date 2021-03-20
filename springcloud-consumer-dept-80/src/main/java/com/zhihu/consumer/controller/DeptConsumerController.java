package com.zhihu.consumer.controller;

import com.zhihu.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@RestController
public class DeptConsumerController {
    // RESTFULL 风格

    // RestTemplate
    @Autowired
    private RestTemplate restTemplate;

    // Ribbon做负载均衡时，不能写死，应该是一个变量，通过服务名来访问
    // eureka中的服务名称
    private static final String REST_URL_PREFIX="http://SPRINGCLOUD-PROVIDER-DEPT";

    //private static final String REST_URL_PREFIX="http://localhost:8001";
    @RequestMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){
        return  restTemplate.getForObject(REST_URL_PREFIX+"/dept/get/"+id,Dept.class);
    }
    @RequestMapping("/consumer/dept/add")
    public Boolean add(Dept dept){
        return  restTemplate.postForObject(REST_URL_PREFIX+"/dept/add",dept,Boolean.class);
    }

    @RequestMapping("/consumer/dept/list")
    public List<Dept> getAll(){
        return  restTemplate.getForObject(REST_URL_PREFIX+"/dept/list", List.class);
    }

}
