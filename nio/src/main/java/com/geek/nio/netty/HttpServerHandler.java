package com.geek.nio.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;

import java.nio.charset.StandardCharsets;

/**
 * @author gaot
 * @date 2021/11/13
 */
public class HttpServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        final FullHttpRequest httpRequest = (FullHttpRequest)msg;
        final String uri = httpRequest.uri();
        if (uri.contains("/index")) {
            handle(ctx, "index");
        } else {
            handle(ctx, "....");
        }
    }

    private void handle(ChannelHandlerContext ctx, String value) {
        final DefaultFullHttpResponse response =
            new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,
                Unpooled.wrappedBuffer(value.getBytes(StandardCharsets.UTF_8)));
        response.headers().add(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON)
            .add(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
        ctx.write(response);
        ctx.flush();
        ctx.close();
    }

}
