package com.gk.rpc.consumer.config;

import com.gk.rpc.core.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaot
 * @date 2021/7/24
 */
@Slf4j
public class Lb implements LoadBalancer {
    private List<String> providerList = new ArrayList<>();



    public Lb() {
        init();
    }

    private void init() {
        final CuratorFramework client =
            CuratorFrameworkFactory.builder().connectString("vmhost:2181").sessionTimeoutMs(15000)
                .connectionTimeoutMs(15000).retryPolicy(new ExponentialBackoffRetry(1000, 3)).namespace("rpc").build();
        client.start();

        String path = "/getUserById";

        final CuratorCache cache = CuratorCache.build(client, path);
        cache.listenable().addListener(CuratorCacheListener.builder().forPathChildrenCache("", client, (c, event) -> {
            if (event.getType() == PathChildrenCacheEvent.Type.CHILD_ADDED) {
                if (StringUtils.hasText(new String(event.getData().getData()))) {
                    providerList.add(new String(event.getData().getData()));
                }
                log.info("{} {}", event.getData().getPath(), new String(event.getData().getData()));
            } else if (event.getType() == PathChildrenCacheEvent.Type.CHILD_REMOVED) {
                providerList.remove(new String(event.getData().getData()));
                log.info("{} {}", event.getData().getPath(), new String(event.getData().getData()));
            }
        }).build());
        cache.start();
        log.info("lb init ...");
    }
    @Override
    public String get() {
        if (CollectionUtils.isEmpty(providerList)) {
            throw new IllegalStateException("provider not exist");
        }
        return providerList.get(0);
    }
}
