package com.gk.gateway.server.route;

import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author gaot
 * @date 2021/11/20
 */
public interface HttpEndpointRouter {
    /**
     * 路线
     *
     * @param request 请求
     * @return {@link String}
     */
    String route(FullHttpRequest request);
}
