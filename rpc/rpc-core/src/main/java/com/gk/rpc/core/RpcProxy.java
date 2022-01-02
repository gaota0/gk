package com.gk.rpc.core;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @author gaot
 * @date 2021/7/24
 */
@Slf4j
public class RpcProxy {
    OkHttpClient client = new OkHttpClient();
    private LoadBalancer loadBalancer;
    private List<Filter> filters;

    public RpcProxy(LoadBalancer loadBalancer, List<Filter> filters) {
        this.loadBalancer = loadBalancer;
        this.filters = filters;
    }

    public <T> T createProxy(Class<T> clazz) {
        final MethodProxy methodProxy = new MethodProxy(clazz);
        final Class[] interfaces = clazz.isInterface() ? new Class[] {clazz} : clazz.getInterfaces();
        return (T)Proxy.newProxyInstance(clazz.getClassLoader(), interfaces, methodProxy);
    }

    private class MethodProxy implements InvocationHandler {
        private Class<?> clazz;

        public MethodProxy(Class<?> clazz) {
            this.clazz = clazz;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (Object.class.equals(method.getDeclaringClass())) {
                try {
                    return method.invoke(this, args);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            } else {
                return rpcInvoke(proxy, method, args);
            }
        }

        private Object rpcInvoke(Object proxy, Method method, Object[] args) {
            final RpcRequest rpcRequest = new RpcRequest();
            rpcRequest.setMethodName(method.getName());
            rpcRequest.setClazzName(this.clazz.getName());
            rpcRequest.setValues(args);
            rpcRequest.setParams(method.getParameterTypes());

            final String providerIp = loadBalancer.get();
            final Request.Builder builder = new Request.Builder().url("http://" + providerIp + ":8080/rpc").post(
                RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSON.toJSONString(rpcRequest)));
            filters.forEach(filter -> filter.filter(builder));
            final Request request = builder.build();

            try {
                return JSON.parseObject(client.newCall(request).execute().body().string(), method.getReturnType());
            } catch (IOException e) {
                log.error(e.getMessage(), e);
                throw new RuntimeException(e);
            }
        }
    }
}
