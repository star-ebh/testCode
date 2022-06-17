package com.example.netty.client;

import cn.hutool.core.thread.ThreadUtil;
import com.example.common.Constant;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author hkh
 */
@Slf4j
public class TcpClientHandler extends SimpleChannelInboundHandler<String> {

    private final Map<String, Object> response;

    public TcpClientHandler(Map<String, Object> response) {
        this.response = response;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        log.info("服务端发送的--->服务端ip【{}】------内容->{}", ctx.channel().remoteAddress(), msg);
    }

    /**
     * 当连接建立好的使用调用
     *
     * @param channelHandlerContext etx
     */
    @Override
    public void channelActive(ChannelHandlerContext channelHandlerContext) {
        log.info("客户端和服务端连接成功 【服务端ip->{}】", channelHandlerContext.channel().remoteAddress());
    }

    /**
     * 断开连接调用
     *
     * @param channelHandlerContext ctx
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) {
        log.info("客户端和服务端断开连接 【服务端ip->{}】", channelHandlerContext.channel().remoteAddress());
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}