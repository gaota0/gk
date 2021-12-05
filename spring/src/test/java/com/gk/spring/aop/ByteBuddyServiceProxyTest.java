package com.gk.spring.aop;

import org.junit.jupiter.api.Test;

/**
 * @author gaot
 * @date 2021/12/5
 */
class ByteBuddyServiceProxyTest {

    @Test
    void getServiceProxy() {
        final IService proxyService = ByteBuddyServiceProxy.getServiceProxy();
        proxyService.serviceHandle();
    }
}