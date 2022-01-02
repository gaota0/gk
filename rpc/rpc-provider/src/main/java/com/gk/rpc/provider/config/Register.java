package com.gk.rpc.provider.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author gaot
 * @date 2021/7/24
 */
@Component
public class Register implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        final CuratorFramework client =
            CuratorFrameworkFactory.builder().connectString("vmhost:2181").sessionTimeoutMs(15000)
                .connectionTimeoutMs(15000).retryPolicy(new ExponentialBackoffRetry(1000, 3)).namespace("rpc").build();

        client.start();
        client.create().creatingParentContainersIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/getUserById/127.0.0.1" , "127.0.0.1".getBytes());
    }
}
