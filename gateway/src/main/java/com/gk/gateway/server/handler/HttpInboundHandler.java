package com.gk.gateway.server.handler;

import com.gk.gateway.http.HttpExecutor;
import com.gk.gateway.server.filter.HttpInboundFilter;
import com.gk.gateway.server.route.DefaultEndpointRouter;
import com.gk.gateway.server.route.HttpEndpointRouter;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.Response;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author gaot
 * @date 2021/11/20
 */
@Slf4j
public class HttpInboundHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    private List<HttpInboundFilter> filterList = new LinkedList<>();
    private HttpEndpointRouter router = new DefaultEndpointRouter();
    private HttpExecutor executor = new HttpExecutor();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        filterList.forEach(filter -> filter.filter(ctx, request));
        log.info("channelRead0 request,{}", request);

        final String url = router.route(request) + request.uri().substring(1);
        executor.get(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                log.error("执行失败", e);
                writeAndFlush(ctx, HttpResponseStatus.INTERNAL_SERVER_ERROR, null);
            }

            @Override
            public void onResponse(Call call, Response rsp) {
                byte[] content = null;
                try {
                    content = rsp.body().bytes();
                } catch (Exception e) {
                    log.error("执行失败", e);
                } finally {
                    //                    writeAndFlush(ctx, HttpResponseStatus.OK, content);

                    // 效果不太好，修改问重定向玩玩
                    final DefaultFullHttpResponse response =
                        new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.FOUND);
                    response.headers().add(HttpHeaderNames.LOCATION, url)
                        .add(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
                    ctx.writeAndFlush(response);
                }
            }
        });
    }

    private void writeAndFlush(ChannelHandlerContext ctx, HttpResponseStatus status, byte[] content) {
        final DefaultFullHttpResponse response =
            new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status, Unpooled.wrappedBuffer(content));
        response.headers().add(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.TEXT_HTML)
            .add(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
        ctx.writeAndFlush(response);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error(cause.getMessage(), cause);
        ctx.close();
    }

    public synchronized HttpInboundHandler addFilter(HttpInboundFilter filter) {
        filterList.add(filter);
        return this;
    }

    public synchronized HttpInboundHandler router(HttpEndpointRouter router) {
        this.router = router;
        return this;
    }
}
