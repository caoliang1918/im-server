package org.zhongweixian.live.pojo;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by caoliang on 2019-07-25
 */
public class IUser extends BaseUser {

    private List<IGroup> groups = new ArrayList<>();
    /**
     * 移动端用户
     */
    private Channel TcpChannel;
    /**
     * H5用户
     */
    private Channel wsChannel;

    private Integer loginType;


    public Channel getTcpChannel() {
        return TcpChannel;
    }

    public void setTcpChannel(Channel tcpChannel) {
        TcpChannel = tcpChannel;
    }

    public Channel getWsChannel() {
        return wsChannel;
    }

    public void setWsChannel(Channel wsChannel) {
        this.wsChannel = wsChannel;
    }

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    public List<IGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<IGroup> groups) {
        this.groups = groups;
    }
}
