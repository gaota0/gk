package com.gk.gateway.server.route;

import io.netty.handler.codec.http.FullHttpRequest;

import static com.gk.gateway.constant.WebsiteSearchUrlConstant.BAIDU;

/**
 * @author gaot
 * @date 2021/11/20
 */
public class DefaultEndpointRouter implements HttpEndpointRouter{
    @Override
    public String route(FullHttpRequest request) {
        return BAIDU;
    }
}
