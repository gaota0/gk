package com.gk.rpc.consumer.config;

import com.gk.rpc.core.RpcProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * @author gaot
 * @date 2021/7/24
 */
@Configuration
public class Config {
    @Bean
    public RpcProxy rpcProxy() {
        return new RpcProxy(lb(), Collections.singletonList(hf()));
    }

    @Bean
    public Lb lb() {
        return new Lb();
    }

    @Bean
    public HeaderFilter hf() {
        return new HeaderFilter();
    }

}
