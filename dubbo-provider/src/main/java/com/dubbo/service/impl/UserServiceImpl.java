package com.dubbo.service.impl;

import com.dubbo.service.UserService;

/**
 * 服务提供者接口的实现
 */
public class UserServiceImpl implements UserService {
    @Override
    public String getName(String userId) {
        return "getName: " + userId;
    }
}
