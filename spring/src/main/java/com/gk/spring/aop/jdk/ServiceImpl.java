package com.gk.spring.aop.jdk;

/**
 * @author gaot
 * @date 2021/7/24
 */
public class ServiceImpl implements IService{
    @Override
    public void serviceHandle() {
        System.out.println("service handle invoke");
    }
}
