package com.gk.spring.aop.jdk;

import org.junit.jupiter.api.Test;

/**
 * @author gaot
 * @date 2021/7/24
 */
public class JdkDynamicProxyTest {
    @Test
    public void testJdkProxy() {
        final JdkDynamicProxy jdkDynamicProxy = new JdkDynamicProxy(new ServiceImpl());
        final IService service= (IService)jdkDynamicProxy.getProxy();
        service.serviceHandle();
    }

}