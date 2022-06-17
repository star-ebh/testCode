//package com.example.tcp.client;
//
//import io.netty.buffer.ByteBuf;
//import io.netty.channel.*;
//import io.netty.util.CharsetUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * <p>@ChannelHandler.Sharable 标注一个channel handler可以被多个channel安全地共享</p>
// *
// * @author hkh
// * @version 1.0.0
// * @ClassName NettyClientHandler
// * @Description NettyClientHandler
// * @createTime 2022/6/8 23:32
// */
//@Slf4j
//@Component
//@ChannelHandler.Sharable
//public class NettyClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
//
//
//    @Autowired
//    private NettyClientService service;
//
//    @Autowired
//    private NettyClient nettyClient;
//
//    /**
//     * @param ctx
//     * @param byteBuf
//     * @Author hkh
//     * @Description 服务端发生消息给客户端，会触发该方法进行接收消息
//     * @Date 23:39 2022/6/8
//     **/
//    @Override
//    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf byteBuf) throws Exception {
//
//        String msg = byteBuf.toString(CharsetUtil.UTF_8);
//
//        log.info("客户端收到消息：{}", msg);
//
//        //service.ackMsg(msg);
//        // 同步消息返回
//        service.ackSyncMsg(msg);
//    }
//
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        log.info("请求连接成功...");
//    }
//
//    @Override
//    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//
//        log.info("连接被断开...");
//
//        // 使用过程中断线重连
//        final EventLoop eventLoop = ctx.channel().eventLoop();
//        eventLoop.schedule(() -> {
//            // 重连
//            nettyClient.start();
//        }, 20, TimeUnit.SECONDS);
//        super.channelInactive(ctx);
//    }
//
//    /**
//     * 处理异常, 一般将实现异常处理逻辑的Handler放在ChannelPipeline的最后
//     * 这样确保所有入站消息都总是被处理，无论它们发生在什么位置，下面只是简单的关闭Channel并打印异常信息
//     *
//     * @param ctx
//     * @param cause
//     * @throws Exception
//     */
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        cause.printStackTrace();
//
//        // 输出到日志中
//        //ExceptionUtil.getStackTrace(cause);
//
//        Channel channel = ctx.channel();
//        if (channel.isActive()) {
//            ctx.close();
//        }
//    }
//
//}
