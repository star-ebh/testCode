package com.example.netty.server;

import cn.hutool.core.thread.ThreadUtil;
import com.example.common.Constant;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 星星泡面
 */
@Slf4j
public class TcpServerHandler extends SimpleChannelInboundHandler<String> {
    /**
     * @param ctx 可以拿到本次处理的上下文信息
     * @param msg 就是获取到的信息
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        log.info("客户端发送的--->{}------内容->{}", ctx.channel().remoteAddress(), msg);
        ThreadUtil.sleep(3000);
        ctx.writeAndFlush("Begin");
        // 处理已测试完成的
//        if (msg.contains(Constant.TCP_FINISH)) {
//            ctx.writeAndFlush("S,1,DUT1,Finish,Pass,0x0D 0x0A");
//        }
    }

    /**
     * @param ctx   当前的应用上下文
     * @param cause Throwable是异常和Error的顶级接口,此处就是异常
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 有异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}