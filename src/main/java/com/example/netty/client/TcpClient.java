package com.example.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author shiyue02
 */

@Slf4j
public class TcpClient {

    private Channel channel;

    /**
     * 初始化 `Bootstrap` 客户端引导程序
     *
     * @param map 返回数据对象
     * @return Bootstrap
     */
    private Bootstrap getBootstrap(Map<String, Object> map) {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();
        bootstrap.group(group)
                // 通道连接者
                .channel(NioSocketChannel.class)
                // 通道处理者
                .handler(new TcpClientInitializer(new TcpClientHandler(map)))
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000);
        return bootstrap;
    }

    /**
     * 建立连接，获取连接通道对象
     */
    public void connect(String host, int port, Map<String, Object> map) {
        ChannelFuture channelFuture = getBootstrap(map).connect(host, port).syncUninterruptibly();
        if (channelFuture != null && channelFuture.isSuccess()) {
            channel = channelFuture.channel();
            log.info("connect tcp server host = {},port = {} success", host, port);
        } else {
            log.error("connect tcp server host = {},port = {} fail", host, port);
        }
    }

    /**
     * 向服务端发送消息
     *
     * @param message 发送信息
     * @throws Exception e
     */
    public void sendMessage(String message) throws Exception {
        if (channel != null) {
            // channel.writeAndFlush(Unpooled.wrappedBuffer(msg.getBytes(CharsetUtil.UTF_8))).sync();
            log.info("【绑定】客户端向服务端({})发送内容->{}", channel.remoteAddress(), message);
            channel.writeAndFlush(message).sync();
            return;
        }
        throw new Exception("消息发送失败，连接尚未建立");
    }

    /**
     * 等待返回数据
     *
     * @throws Exception e
     */
    public void waitsResult() throws Exception {
        if (channel != null) {
            channel.closeFuture().sync();
            return;
        }
        throw new Exception("消息发送失败，连接尚未建立");

    }

    public void destroy() {
        try {
            if (channel != null) {
                channel.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
        }
        log.info("Netty tcp client shutdown success");
    }
}




