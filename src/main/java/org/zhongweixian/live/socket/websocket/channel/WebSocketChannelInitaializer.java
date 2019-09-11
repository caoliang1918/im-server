package org.zhongweixian.live.socket.websocket.channel;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.zhongweixian.live.socket.websocket.handler.TextWebSocketFrameHandler;
import org.zhongweixian.live.socket.websocket.WsMessgaeService;

public class WebSocketChannelInitaializer extends ChannelInitializer<Channel> {

    private WsMessgaeService messgaeService;

    public WebSocketChannelInitaializer(WsMessgaeService messgaeService) {
        this.messgaeService = messgaeService;
    }

    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        //心跳检测
        pipeline.addLast("timeout", new IdleStateHandler(60, 0, 0));
        //HttpServerCodec: 针对http协议进行编解码
        pipeline.addLast(new HttpServerCodec());
        //ChunkedWriteHandler分块写处理，文件过大会将内存撑爆
        pipeline.addLast(new ChunkedWriteHandler());
        pipeline.addLast(new HttpObjectAggregator(8192));
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));


        //自定义的处理器
        pipeline.addLast(new TextWebSocketFrameHandler(messgaeService));
    }
}
