package com.wdl.service;

import com.dubbo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerService {
    /**
     * 该接口实现来自于dubbo中注册的服务端
     */
    @Autowired
    private DemoService demoService;

    public List<String> getPermissions(Long id) {
        return demoService.getPermissions(id);
    }
}
