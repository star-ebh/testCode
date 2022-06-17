package com.example.netty.client;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.util.CharsetUtil;

/**
 * @author 星星泡面
 */
public class TcpClientInitializer extends ChannelInitializer<SocketChannel> {

    private final TcpClientHandler clientHandler;

    public TcpClientInitializer(TcpClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    @Override
    protected void initChannel(SocketChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();
        // 设置90秒未读自动断开连接
        ch.pipeline().addLast(new ReadTimeoutHandler(90));
        pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8), new StringEncoder(CharsetUtil.UTF_8));
        pipeline.addLast(clientHandler);
    }
}
