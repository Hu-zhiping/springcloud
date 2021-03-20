package com.zhihu.controller;

import com.zhihu.pojo.Dept;
import com.zhihu.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/dept/add")
    public boolean addDept(Dept dept){
        return deptService.addDept(dept);
    }
    @GetMapping ("/dept/get/{id}")
    public Dept queryById(@PathVariable("id") Long id){
        return deptService.queryById(id);
    }
    @GetMapping("/dept/list")
    public List<Dept> queryAll(){
        return deptService.queryAll();
    }

    // 获取注册进来的微服务信息
    public Object discovery(){
        // 获取服务清单
        List<String> services = client.getServices();

        List<ServiceInstance> instances = client.getInstances("SPRINGCLOUD-PROVIDER-DEPT");
        for (ServiceInstance instance:instances
             ) {
            instance.getHost();
            instance.getPort();
            instance.getUri();
        }
        return this.client;
    }
}
