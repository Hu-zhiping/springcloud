package com.zhihu.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zhihu.pojo.Dept;
import com.zhihu.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.GsonTester;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.DocFlavor;
import java.util.List;

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    // 获取具体微服务的信息
    @Autowired
    DiscoveryClient client;

    @HystrixCommand(fallbackMethod = "hystrixGet")
    @GetMapping ("/dept/get/{id}")
    public Dept queryById(@PathVariable("id") Long id){
        Dept dept=deptService.queryById(id);
        if (dept ==null){
            throw new RuntimeException("id->"+id+",不存在！");
        }
        return dept;
    }

    /**
     * 熔断，备用方案
     * @param id
     * @return
     */
    public Dept hystrixGet(@PathVariable("id") Long id){
        return new Dept().setDeptno(id).setDname("id->"+id+"不存在，null--@Hystrix").setDb_source("没有这个数据库！");
    }

    // 获取注册进来的微服务信息
    public Object discovery(){
        // 获取服务清单
        List<String> services = client.getServices();

        List<ServiceInstance> instances = client.getInstances("SPRINGCLOUD-PROVIDER-DEPT");
        for (ServiceInstance instance:instances
             ) {
            System.out.println( instance.getHost()+","+instance.getPort()+","+instance.getUri());
        }
        return this.client;
    }
}
