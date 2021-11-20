package com.gk.gateway.server.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author gaot
 * @date 2021/11/20
 */
public interface HttpInboundFilter {

    /**
     * 过滤器
     *
     * @param context 上下文
     * @param request 请求
     */
    void filter(ChannelHandlerContext context, FullHttpRequest request);
}
