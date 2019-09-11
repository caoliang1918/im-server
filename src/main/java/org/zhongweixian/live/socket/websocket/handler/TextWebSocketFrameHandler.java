package org.zhongweixian.live.socket.websocket.handler;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zhongweixian.live.socket.tcp.ChannelRepository;
import org.zhongweixian.live.socket.websocket.WsMessgaeService;

import java.util.Date;

public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private Logger logger = LoggerFactory.getLogger(TextWebSocketFrameHandler.class);
    private WsMessgaeService wsMessgaeService;

    public TextWebSocketFrameHandler(WsMessgaeService wsMessgaeService) {
        this.wsMessgaeService = wsMessgaeService;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) {
        if (StringUtils.isNoneEmpty(textWebSocketFrame.text())) {
            try {
                JSONObject jsonObject = JSONObject.parseObject(textWebSocketFrame.text());
                if (jsonObject != null) {
                    logger.info("接受到客户端消息:{}", jsonObject);
                    wsMessgaeService.readMessage(jsonObject, channelHandlerContext.channel());
                }
            } catch (Exception e) {
                logger.error("解析json:{} 异常:{}", textWebSocketFrame.text(), e);
                JSONObject error = new JSONObject();
                error.put("messgae", e.getMessage());
                error.put("code", 500);
                channelHandlerContext.channel().writeAndFlush(new TextWebSocketFrame(error.toJSONString()));
            }
        }
    }

    /**
     * 取消绑定
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        this.wsMessgaeService.logout(ctx.channel());
    }

    /**
     * 心跳机制  用户事件触发
     *
     * @param channelHandlerContext
     * @param evt
     * @throws Exception
     */

    @Override
    public void userEventTriggered(ChannelHandlerContext channelHandlerContext, Object evt) throws Exception {
        super.userEventTriggered(channelHandlerContext, evt);
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            if (idleStateEvent.state() == IdleState.READER_IDLE) {
                logger.warn("60秒没有收到客户端信息,关闭连接！");
                //向客户端发送心跳消息
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", 1L);
                jsonObject.put("cmd", "logout");
                jsonObject.put("message", "60秒没有收到客户端信息,关闭连接");
                jsonObject.put("cts", new Date());
                channelHandlerContext.writeAndFlush(new TextWebSocketFrame(jsonObject.toString()));
                channelHandlerContext.channel().close();
            }
        }
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        logger.info("register success");
        ctx.fireChannelRegistered();
        NioSocketChannel channel = (NioSocketChannel) ctx.channel();
        ChannelRepository.put(channel.id().toString(), channel);
    }
}
