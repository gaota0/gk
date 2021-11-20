package com.gk.gateway.server;

import com.gk.gateway.server.initializer.HttpChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author gaot
 * @date 2021/11/20
 */
@Slf4j
public class GatewayApplication {
    public static final int PORT = 80;

    public static void main(String[] args) {
        final NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        final NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            final ServerBootstrap serverBootstrap =
                new ServerBootstrap().group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    // 服务端接受连接的队列长度
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .childHandler(new HttpChannelInitializer());
            final Channel channel = serverBootstrap.bind(PORT).sync().channel();
            log.info("gateway 启动,port:{}", PORT);
            channel.closeFuture().sync();
        } catch (Exception e) {
            log.error("", e);
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
