package org.zhongweixian.live.socket.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.googlecode.protobuf.format.JsonFormat;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelId;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.zhongweixian.live.cache.SystemCacheService;
import org.zhongweixian.live.pojo.IGroup;
import org.zhongweixian.live.pojo.IUser;
import org.zhongweixian.live.protocol.Command;
import org.zhongweixian.live.protocol.LoginMessage;
import org.zhongweixian.live.socket.websocket.channel.WebSocketChannelInitaializer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.zhongweixian.live.protocol.Command.CMD_LOGIN_RESP;

@Component
public class WsServerStart {
    private Logger logger = LoggerFactory.getLogger(WsServerStart.class);

    @Value("${ws.port}")
    private int port;

    @Autowired
    private SystemCacheService cacheService;

    private String[] images = new String[]{
            "https://tva4.sinaimg.cn/crop.0.0.750.750.180/67b532d1jw8eyo1kwlfmsj20ku0kudh7.jpg",
            "https://tva3.sinaimg.cn/crop.8.0.623.623.180/95b70082jw8eo6opg30taj20hs0hbju6.jpg",
           };


    public String getRandomImages() {
        int i = (int) (Math.random() * images.length);
        return images[i];
    }

    private Map<ChannelId, IUser> userMap = new HashMap<ChannelId, IUser>();


    private EventLoopGroup boosGrop = new NioEventLoopGroup();
    private EventLoopGroup workerGrop = new NioEventLoopGroup();

    @PostConstruct
    private void start() {
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //使用服务端初始化自定义类WebSocketChannelInitaializer
            serverBootstrap.group(boosGrop, workerGrop).channel(NioServerSocketChannel.class).
                    localAddress(new InetSocketAddress(port)).
                    childHandler(new WebSocketChannelInitaializer(new WsMessgaeService() {
                        @Override
                        public void readMessage(JSONObject jsonObject, Channel channel) throws Exception {
                            Command command = Command.valueOf(jsonObject.getString("cmd"));
                            IUser user = userMap.get(channel.id());
                            IGroup group;
                            switch (command) {
                                case CMD_LOGIN:
                                    LoginMessage.Builder builder = LoginMessage.newBuilder();
                                    JsonFormat.merge(jsonObject.toString(), builder);
                                    logger.info("{}", builder.build());
                                    LoginMessage loginMessage = builder.build();

                                    /**
                                     * 避免重复登录
                                     */
                                    IUser iUser = cacheService.getUser(loginMessage.getUsername());
                                    if (iUser != null && iUser.getWsChannel() != null) {
                                        logger.warn("{}", iUser);
                                        Channel existChannel = iUser.getWsChannel();
                                        if (existChannel != null && existChannel.isOpen()) {
                                            channel.writeAndFlush(new TextWebSocketFrame("{'type':'EXIST_LOGIN'}"));
                                            channel.close();
                                            return;
                                        }
                                    }

                                    iUser = new IUser();
                                    String avatar = getRandomImages();
                                    iUser.setWsChannel(channel);
                                    iUser.setUsername(loginMessage.getUsername());
                                    iUser.setAvatar(avatar);
                                    String nikename = RandomStringUtils.randomAlphabetic(3);
                                    iUser.setNick(nikename);
                                    userMap.put(channel.id(), iUser);
                                    cacheService.userLogin(iUser);

                                    /**
                                     * 校验
                                     */
                                    logger.info("校验用户密码 , username:{} , password:{} ", loginMessage.getUsername(), loginMessage.getPassword());
                                    JSONObject response = new JSONObject();
                                    response.put("type", CMD_LOGIN_RESP);
                                    response.put("code", 0);
                                    response.put("msg", "登录[" + jsonObject.getString("deviceType") + "]成功");
                                    channel.writeAndFlush(new TextWebSocketFrame(response.toString()));
                                    channel.writeAndFlush(new TextWebSocketFrame("{'type':'CMD_JOIN_GROUP_NOTIFY','data':{'group':'100','user':{'id':'" + loginMessage.getUsername() + "','nick':'" + nikename + "'}}}"));

                                    JSONObject initGroup = new JSONObject();
                                    initGroup.put("type", Command.CMD_JOIN_GROUP);
                                    initGroup.put("msg", "ok 获取所有在线用户信息成功!");


                                    JSONObject data = new JSONObject();
                                    data.put("avatar", avatar);
                                    data.put("id", iUser.getUsername());
                                    data.put("nick", nikename);
                                    data.put("terminal", "ws");

                                    group = cacheService.addGroupUser(100, iUser);
                                    List<IGroup> groups = new ArrayList<>();
                                    groups.add(group);
                                    data.put("groups", groups);
                                    initGroup.put("data", data);
                                    iUser.getGroups().add(group);

                                    channel.writeAndFlush(new TextWebSocketFrame(initGroup.toJSONString()));

                                    group.getUsers().forEach(u -> {
                                        if (u.getWsChannel() != null && !channel.id().equals(u.getWsChannel().id())) {
                                            logger.info("群:{}  , {} 上线通知", group.getId(), u.getUsername());
                                            u.getWsChannel().writeAndFlush(new TextWebSocketFrame("{'type':'CMD_JOIN_GROUP_NOTIFY','data':{'group':'100','user':{'id':'" + loginMessage.getUsername() + "','nick':'" + nikename + "'}}}"));
                                            data.put("id", u.getUsername());
                                            data.put("nick", u.getNick());
                                            initGroup.put("data", data);
                                            u.getWsChannel().writeAndFlush(new TextWebSocketFrame(initGroup.toJSONString()));
                                        }
                                    });
                                    break;

                                case CMD_GROUP_CHAT:
                                    /**
                                     * 群消息
                                     */
                                    Integer groupId = jsonObject.getInteger("groupId");
                                    if (groupId == null) {
                                        return;
                                    }
                                    group = cacheService.getGroup(groupId);
                                    if (group == null || group.getUsers().size() <= 1) {
                                        return;
                                    }
                                    group.getUsers().forEach(u -> {
                                        if (u.getWsChannel() != null && !channel.id().equals(u.getWsChannel().id())) {
                                            logger.info("来自{} 发的群,groupId:{}消息 content:{}", user.getUsername(), group.getId(), jsonObject.getString("content"));
                                            u.getWsChannel().writeAndFlush(new TextWebSocketFrame("{'type':'CMD_GROUP_CHAT','data':{'chatType':1,'cmd':11,'content':'" + jsonObject.getString("content") + "','cts':" + System.currentTimeMillis() + ",'from':'" + user.getUsername() + "','groupId':'100','id':'f9927e3b700d475fa525396155d75e13','msgType':0}}"));
                                        }
                                    });
                                    channel.writeAndFlush(new TextWebSocketFrame("{'code':10000,'command':12,'msg':'ok 发送成功'}"));
                                    break;
                            }
                        }

                        @Override
                        public void logout(Channel channel) {
                            IUser user = userMap.get(channel.id());
                            userMap.remove(channel.id());
                            if (user == null) {
                                return;
                            }
                            user.getGroups().forEach(g -> {
                                g.getUsers().remove(user);
                            });
                            logger.info("user:{} logout", user.getUsername());
                            IGroup group = user.getGroups().get(0);
                            if (group == null) {
                                return;
                            }
                            group.getUsers().forEach(u -> {
                                u.getWsChannel().writeAndFlush(new TextWebSocketFrame("{'type':'CMD_LEAVE_GROUP','data':{'group':'100','user':{'id':'" + u.getUsername() + "','nick':'" + u.getNick() + "'}}}"));
                            });
                        }
                    }));
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            if (channelFuture.isSuccess()) {
                logger.info("websocket started on port :{} (websocket)", port);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void destory() {
        boosGrop.shutdownGracefully().syncUninterruptibly();
        workerGrop.shutdownGracefully().syncUninterruptibly();
        logger.info("websocket stop");
    }
}
