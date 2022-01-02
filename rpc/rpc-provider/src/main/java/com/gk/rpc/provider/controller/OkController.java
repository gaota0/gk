package com.gk.rpc.provider.controller;

import com.alibaba.fastjson.JSON;
import com.gk.rpc.api.intf.UserService;
import com.gk.rpc.api.model.User;
import com.gk.rpc.core.RpcProxy;
import com.gk.rpc.core.RpcRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author gaot
 * @date 2021/7/24
 */
@RestController
public class OkController {
    @Autowired
    private ApplicationContext context;

    @PostMapping("rpc")
    public String getUser(@RequestBody RpcRequest request) throws  Exception {
        final Object bean = context.getBean(request.getClazzName());
        final Method method = bean.getClass().getMethod(request.getMethodName(), request.getParams());
        return JSON.toJSONString(method.invoke(bean, request.getValues()));
    }
}
