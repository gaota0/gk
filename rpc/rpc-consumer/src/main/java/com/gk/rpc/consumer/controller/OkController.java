package com.gk.rpc.consumer.controller;

import com.gk.rpc.api.intf.UserService;
import com.gk.rpc.api.model.User;
import com.gk.rpc.core.RpcProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gaot
 * @date 2021/7/24
 */
@RestController
public class OkController {
    @Autowired
    private RpcProxy rpcProxy;
    @GetMapping("user")
    public User getUser() {
        final UserService service = rpcProxy.createProxy(UserService.class);
        return service.queryById(1);
    }
}
