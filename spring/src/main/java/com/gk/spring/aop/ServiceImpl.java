package com.gk.spring.aop;

import lombok.extern.slf4j.Slf4j;

/**
 * @author gaot
 * @date 2021/7/24
 */
public class ServiceImpl implements IService{
    @Override
    public void serviceHandle() {
        System.out.println("service handle invoke");
    }
    public ServiceImpl() {

    }
}
