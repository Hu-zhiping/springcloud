package com.zhihu.service;

import com.zhihu.pojo.Dept;
import feign.hystrix.FallbackFactory;

import java.util.List;

/**
 * 服务降级
 */
public class DeptServiceFallbackFactory implements FallbackFactory {
    @Override
    public DeptService create(Throwable throwable) {
        return new DeptService() {
            @Override
            public Dept queryById(Long id) {
                return new Dept().setDeptno(id)
                        .setDname("id->"+id+"没有对应信息，客户端提供降级信息，这个服务已经被关闭")
                        .setDb_source("没有数据");
            }

            @Override
            public List<Dept> queryAll() {
                return null;
            }

            @Override
            public boolean addDept(Dept dept) {
                return false;
            }
        };
    }
}
