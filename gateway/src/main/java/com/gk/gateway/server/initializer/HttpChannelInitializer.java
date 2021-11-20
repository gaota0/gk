package com.gk.gateway.server.initializer;

import com.gk.gateway.server.handler.HttpInboundHandler;
import com.gk.gateway.server.handler.HttpOutboundHandler;
import com.gk.gateway.server.route.RandomEndpointRouter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author gaot
 * @date 2021/11/20
 */
public class HttpChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        final HttpInboundHandler httpInboundHandler = new HttpInboundHandler().router(new RandomEndpointRouter())
            .addFilter((context, request) -> request.headers().add("httpReqFilter", "in"));

        final HttpOutboundHandler httpOutboundHandler =
            new HttpOutboundHandler().addFilter((context, response) -> response.headers().add("httpRspFilter", "out"));

        final ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new HttpServerCodec()).addLast(new HttpObjectAggregator(1024 * 1024))
            .addLast(httpOutboundHandler).addLast(httpInboundHandler);
    }
}
