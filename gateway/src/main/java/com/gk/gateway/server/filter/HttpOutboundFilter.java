package com.gk.gateway.server.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpResponse;

/**
 * @author gaot
 * @date 2021/11/20
 */
public interface HttpOutboundFilter {
    /**
     * 过滤器
     *
     * @param context  上下文
     * @param response 响应
     */
    void filter(ChannelHandlerContext context, FullHttpResponse response);
}
