package org.zhongweixian.live.socket.tcp.channel;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.timeout.IdleStateHandler;
import org.zhongweixian.live.socket.tcp.decoder.MessageDecoder;
import org.zhongweixian.live.socket.tcp.handler.NettyServerHandler;
import org.zhongweixian.live.socket.tcp.TcpMessageService;

/**
 * <p>
 * 服务端
 */
public class NettyChannelInitializer extends ChannelInitializer<Channel> {

    private TcpMessageService tcpMessageService;

    public NettyChannelInitializer(TcpMessageService tcpMessageService) {
        this.tcpMessageService = tcpMessageService;
    }

    @Override
    protected void initChannel(Channel channel) throws Exception {

        channel.pipeline().addLast("timeout", new IdleStateHandler(60, 0, 0))
                .addLast(new MessageDecoder())
                .addLast(new NettyServerHandler(tcpMessageService));

    }
}
