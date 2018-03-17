package com.dubbo.service;

import java.util.List;

/**
 * 给服务提供者和消费者公用的接口
 */
public interface DemoService {
    List<String> getPermissions(Long id);
}
