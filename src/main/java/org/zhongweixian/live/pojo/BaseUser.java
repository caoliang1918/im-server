package org.zhongweixian.live.pojo;

import java.io.Serializable;

/**
 * Created by caoliang on 2019-07-25
 */
public class BaseUser {
    public Integer id;

    public String username;

    public String avatar;

    public String nick;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
