package com.gk.rpc.provider;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;

import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * @author gaot
 * @date 2021/7/24
 */
@Slf4j
public class Client {
    public static void main(String[] args) throws Exception {
        final CuratorFramework client =
            CuratorFrameworkFactory.builder().connectString("vmhost:2181").sessionTimeoutMs(15000)
                .connectionTimeoutMs(15000).retryPolicy(new ExponentialBackoffRetry(1000, 3)).namespace("rpc").build();
        client.start();
        String path = "/hello";

        final CuratorCache cache = CuratorCache.build(client, path);
        cache.listenable().addListener(CuratorCacheListener.builder().forPathChildrenCache(
            "/hello", client, (client1, event) -> {
                if (event.getType() == PathChildrenCacheEvent.Type.INITIALIZED) {
                    System.out.println("create" + event.getData().getPath());
                } else if (event.getType() == PathChildrenCacheEvent.Type.CHILD_ADDED) {
                    System.out.println("create" + event.getData().getPath());
                } else if (event.getType() == PathChildrenCacheEvent.Type.CHILD_REMOVED) {
                    System.out.println("remove:" + event.getData().getPath());
                } else if (event.getType() == PathChildrenCacheEvent.Type.CHILD_UPDATED) {
                    //System.out.println("update:"+event.getData().getPath());
                    System.out.println("update:" + new String(event.getData().getData()));
                }
            }).build());
cache.start();
        //创建父节点
            client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path, "init".getBytes());
        Thread.sleep(1000);

        //创建子节点
        String childPath1 = ZKPaths.makePath(path, "a");
        childPath1 = client.create().withMode(CreateMode.PERSISTENT).forPath(childPath1, "1".getBytes());
        Thread.sleep(1000);

        //对子节点赋值
        client.setData().forPath(childPath1, "aaa".getBytes());
        Thread.sleep(1000);

        //删除子节点
        client.delete().forPath(childPath1);
        client.delete().deletingChildrenIfNeeded().forPath(path);

        Thread.sleep(2000);
        //
        //
        //
        //        final CuratorCache cache = CuratorCache.build(client, "/rpc");
        //        CuratorCacheListener listener = CuratorCacheListener.builder()
        //            .forPathChildrenCache("/", client, new PathChildrenCacheListener() {
        //                @Override
        //                public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
        //                    System.out.println("发生了变化"+event);
        //                }
        //            })
        //            .build();
        //
        //        // register the listener
        //        cache.listenable().addListener(listener);
        //
        //
        //
        //        // the cache must be started
        //        cache.start();

        Thread.sleep(100000000);
    }
}
