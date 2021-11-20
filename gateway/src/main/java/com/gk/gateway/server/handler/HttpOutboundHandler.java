package com.gk.gateway.server.handler;

import com.gk.gateway.server.filter.HttpOutboundFilter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.FullHttpResponse;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author gaot
 * @date 2021/11/20
 */
public class HttpOutboundHandler extends ChannelOutboundHandlerAdapter {
    private List<HttpOutboundFilter> filterList = new LinkedList<>();

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        filterList.forEach(filter -> filter.filter(ctx, (FullHttpResponse)msg));
        super.write(ctx,msg,promise);
    }

    public synchronized HttpOutboundHandler addFilter(HttpOutboundFilter filter) {
        filterList.add(filter);
        return this;
    }
}
