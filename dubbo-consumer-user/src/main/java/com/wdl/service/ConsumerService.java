package com.wdl.service;

import com.dubbo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    /**
     * 该接口的实现从dubbo中获取
     */
    @Autowired
    private UserService userService;

    public void getName(String name) {
        System.out.println(userService.getName(name));
    }
}
