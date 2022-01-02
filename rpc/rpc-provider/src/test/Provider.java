package com.gk.rpc.provider;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author gaot
 * @date 2021/7/24
 */
public class Provider {
    public static void main(String[] args) throws Exception {
        final CuratorFramework client =
            CuratorFrameworkFactory.builder().connectString("vmhost:2181").sessionTimeoutMs(15000)
                .connectionTimeoutMs(15000).retryPolicy(new ExponentialBackoffRetry(1000, 3)).namespace("rpc").build();

        client.start();
        client.create().withMode(CreateMode.EPHEMERAL).forPath("/127.0.0.1" , "127.0.0.1".getBytes());
        System.out.println("127.0.0.1 上线了");
        TimeUnit.HOURS.sleep(1);
    }
}
