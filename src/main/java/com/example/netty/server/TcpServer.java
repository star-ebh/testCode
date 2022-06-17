package com.example.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;


/**
 * @author shiyue02
 */
@Slf4j
public class TcpServer {
    private Channel channel;

    private final int port;

    private final EventLoopGroup workerGroup = new NioEventLoopGroup();

    public TcpServer(int port) {
        this.port = port;
    }

    public void start() {
        log.info("netty Tcp 服务器启动");
        // 负责连接请求
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // 负责事件响应
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // 服务器启动项
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //handler是针对bossGroup，childHandler是针对workerHandler
            serverBootstrap.group(bossGroup, workerGroup)
                    // 选择nioChannel
                    .channel(NioServerSocketChannel.class)
                    // socket参数，当服务器请求处理程全满时，用于临时存放已完成三次握手请求的队列的最大长度。如果未设置或所设置的值小于1，Java将使用默认值50。
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    // 日志处理 info级别
                    .handler(new LoggingHandler(LogLevel.INFO))
                    // 添加自定义的初始化器
                    .childHandler(new TcpServerInitializer())
                    // 启用心跳保活机制，tcp，默认2小时发一次心跳
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            // 端口绑定
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            if (channelFuture != null && channelFuture.isSuccess()) {
                // 获取通道
                channel = channelFuture.channel();
                log.info("Netty tcp server start success");
            } else {
                log.error("Netty tcp server start fail");
            }
            // 该方法进行阻塞,等待服务端链路关闭之后继续执行。
            //这种模式一般都是使用Netty模块主动向服务端发送请求，然后最后结束才使用
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 关闭
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

    /**
     * 停止Netty tcp server服务
     */
    public void destroy() {
        if (channel != null) {
            channel.close();
        }
        try {
            Future<?> future = workerGroup.shutdownGracefully().await();
            if (!future.isSuccess()) {
                log.error("netty tcp workerGroup shutdown fail->,", future.cause());
            }
        } catch (InterruptedException e) {
            log.error(e.toString());
        }
        log.info("Netty tcp server shutdown success");
    }
}



