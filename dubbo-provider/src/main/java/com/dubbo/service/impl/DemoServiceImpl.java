package com.dubbo.service.impl;

import com.dubbo.service.DemoService;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务提供者接口的实现
 */
public class DemoServiceImpl implements DemoService {
    @Override
    public List<String> getPermissions(Long id) {
        List<String> demo = new ArrayList<String>();
        demo.add(String.format("Permission_%d", id - 1));
        demo.add(String.format("Permission_%d", id));
        demo.add(String.format("Permission_%d", id + 1));
        return demo;
    }
}
