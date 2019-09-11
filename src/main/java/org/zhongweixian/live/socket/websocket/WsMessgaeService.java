package org.zhongweixian.live.socket.websocket;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.Channel;


public interface WsMessgaeService {

    /**
     * 接受消息
     *
     * @param jsonObject
     */
    void readMessage(JSONObject jsonObject, Channel channel) throws Exception;


    /**
     * 用户断开
     * @param channel
     */
    void logout(Channel channel);
}
