package com.gk.gateway.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

/**
 * @author gaot
 * @date 2021/11/21
 */
@Slf4j
public class Client {
    private ClientHandler clientHandler = new ClientHandler();

    public void connect(String host, int port) {

        final NioEventLoopGroup group = new NioEventLoopGroup();
        new Thread(() -> {
            try {
                final Bootstrap bootstrap = new Bootstrap().group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true).handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new HttpClientCodec()).addLast(new HttpObjectAggregator(1024 * 1024))
                                .addLast(clientHandler);
                        }
                    });
                final ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
                channelFuture.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            } finally {
                group.shutdownGracefully();
            }
        }).start();

    }

    public void sendMsg(String msg) {
        clientHandler.sendMsg(msg);
    }
}
