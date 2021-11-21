package com.gk.gateway.client;

import com.sun.jndi.toolkit.url.Uri;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.nio.charset.StandardCharsets;

/**
 * @author gaot
 * @date 2021/11/21
 */
@Slf4j
public class ClientHandler extends SimpleChannelInboundHandler<HttpObject> {
    private ChannelHandlerContext context;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.context = ctx;
        log.info("客户端成功连接服务器");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        log.info("客户端收到服务器响应:{}", msg);
        if (msg instanceof FullHttpResponse) {
            final FullHttpResponse response = (FullHttpResponse)msg;
            final String result = response.content().toString(StandardCharsets.UTF_8);
            log.info("服务器响应内容:{}", result);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("客户端断开连接", cause);
        ctx.close();
    }

    public void sendMsg(String msg) {
        final FullHttpRequest httpRequest = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, msg);
        httpRequest.headers().add(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE)
            .add(HttpHeaderNames.CONTENT_LENGTH, httpRequest.content().readableBytes());
        this.context.channel().writeAndFlush(httpRequest);
    }
}
