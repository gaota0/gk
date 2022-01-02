package com.gk.rpc.provider.config;

import com.gk.rpc.provider.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author gaot
 * @date 2021/7/24
 */
@Configuration
public class ServiceConfig {
    @Bean("com.gk.rpc.api.intf.UserService")
    public UserServiceImpl userService() {
        return new UserServiceImpl();
    }
}
