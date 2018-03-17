package com.dubbo.service;

/**
 * 给服务提供者和消费者公用的接口
 */
public interface UserService {
    String getName(String userId);
}
