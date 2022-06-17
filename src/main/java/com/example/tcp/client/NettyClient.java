//package com.example.tcp.client;
//
//import io.netty.bootstrap.Bootstrap;
//import io.netty.buffer.ByteBuf;
//import io.netty.buffer.Unpooled;
//import io.netty.channel.*;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.channel.socket.nio.NioSocketChannel;
//import io.netty.util.CharsetUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.util.concurrent.TimeUnit;
//
///**
// * @author hkh
// * @version 1.0.0
// * @ClassName NettyClient
// * @Description NettyClient
// * @createTime 2022/6/8 23:30
// */
//@Slf4j
//@Component
//public class NettyClient {
//
//
//    private EventLoopGroup group = new NioEventLoopGroup();
//
//    /**
//     * @Fields DELIMITER : 自定义分隔符，服务端和客户端要保持一致
//     */
//    public static final String DELIMITER = "@@";
//
//    /**
//     * @Fields hostIp : 服务端ip
//     */
//    private String hostIp = "127.0.0.1";
//
//    /**
//     * @Fields port : 服务端端口
//     */
//    private int port = 8888;
//
//    /**
//     * @Fields socketChannel : 通道
//     */
//    private SocketChannel socketChannel;
//
//
//    /**
//     * @Fields clientHandlerInitilizer : 初始化
//     */
//    @Autowired
//    private NettyClientHandlerInitilizer clientHandlerInitilizer;
//
//    /**
//     * @Author hkh
//     * @Description 启动客户端
//     * @Date 23:40 2022/6/8
//     **/
//    @SuppressWarnings("unchecked")
//    @PostConstruct
//    public void start() {
//
//        Bootstrap bootstrap = new Bootstrap();
//        bootstrap.group(group)
//                // 指定Channel
//                .channel(NioSocketChannel.class)
//                // 服务端地址
//                .remoteAddress(hostIp, port)
//                // 将小的数据包包装成更大的帧进行传送，提高网络的负载,即TCP延迟传输
//                .option(ChannelOption.SO_KEEPALIVE, true)
//                // 将小的数据包包装成更大的帧进行传送，提高网络的负载,即TCP延迟传输
//                .option(ChannelOption.TCP_NODELAY, true)
//                .handler(clientHandlerInitilizer);
//
//        // 连接
//        ChannelFuture channelFuture = bootstrap.connect();
//        //客户端断线重连逻辑
//        channelFuture.addListener((ChannelFutureListener) future -> {
//            if (future.isSuccess()) {
//                log.info("连接Netty服务端成功...");
//            } else {
//
//                log.info("连接Netty服务端失败，进行断线重连...");
//                final EventLoop loop = future.channel().eventLoop();
//                loop.schedule(new Runnable() {
//                    @Override
//                    public void run() {
//                        log.info("连接正在重试...");
//                        start();
//                    }
//                }, 20, TimeUnit.SECONDS);
//            }
//        });
//
//        socketChannel = (SocketChannel) channelFuture.channel();
//    }
//
//
//    /**
//     * @param message 消息
//     * @Author hkh
//     * @Description 发送消息
//     * @Date 23:38 2022/6/8
//     **/
//    public void sendMsg(String message) {
//
//        String msg = message.concat(NettyClient.DELIMITER);
//
//        ByteBuf byteBuf = Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8);
//        ChannelFuture future = socketChannel.writeAndFlush(byteBuf);
//
//        future.addListener(new ChannelFutureListener() {
//
//            @Override
//            public void operationComplete(ChannelFuture future) throws Exception {
//
//                if (future.isSuccess()) {
//                    System.out.println("===========发送成功");
//                } else {
//                    System.out.println("------------------发送失败");
//                }
//            }
//        });
//    }
//
//
//    /**
//     * @param message
//     * @param syncFuture
//     * @return java.lang.String
//     * @Author hkh
//     * @Description 发送同步消息
//     * @Date 23:40 2022/6/8
//     **/
//    public String sendSyncMsg(String message, SyncFuture<String> syncFuture) {
//
//        String result = "";
//
//        String msg = message.concat(NettyClient.DELIMITER);
//
//        ByteBuf byteBuf = Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8);
//
//        try {
//
//            ChannelFuture future = socketChannel.writeAndFlush(byteBuf);
//            future.addListener(new ChannelFutureListener() {
//
//                @Override
//                public void operationComplete(ChannelFuture future) throws Exception {
//
//                    if (future.isSuccess()) {
//                        System.out.println("===========发送成功");
//                    } else {
//                        System.out.println("------------------发送失败");
//                    }
//                }
//            });
//
//            // 等待 8 秒
//            result = syncFuture.get(8, TimeUnit.SECONDS);
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        return result;
//    }
//
//    public String getHostIp() {
//        return hostIp;
//    }
//
//
//    public void setHostIp(String hostIp) {
//        this.hostIp = hostIp;
//    }
//
//
//    public int getPort() {
//        return port;
//    }
//
//
//    public void setPort(int port) {
//        this.port = port;
//    }
//
//
//}