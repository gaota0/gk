package com.gk.spring.aop;

import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author gaot
 * @date 2021/7/24
 */
@AllArgsConstructor
public class JdkDynamicProxy implements InvocationHandler {
    private Object service;

    public Object getProxy() {
        return Proxy.newProxyInstance(service.getClass().getClassLoader(), service.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("jdk dynamic proxy handle start");
        final Object result = method.invoke(service, args);
        System.out.println("jdk dynamic proxy handle end");
        return result;
    }
}
