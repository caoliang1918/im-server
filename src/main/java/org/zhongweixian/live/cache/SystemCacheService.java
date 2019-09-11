package org.zhongweixian.live.cache;

import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.zhongweixian.live.pojo.IGroup;
import org.zhongweixian.live.pojo.IUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by caoliang on 2019-07-26
 */

@Service
public class SystemCacheService {
    private Logger logger = LoggerFactory.getLogger(SystemCacheService.class);


    private Map<Integer, IUser> userCache = new HashMap<>();
    private Map<Integer, IGroup> groupCache = new HashMap<>();


    public IGroup getGroup(Integer id) {
        return groupCache.get(id);
    }

    public IGroup addGroupUser(Integer id, IUser iUser) {
        IGroup group = groupCache.get(id);
        if (group == null) {
            group = new IGroup();
            group.setGroupName("默认群组");
            group.setId(100);
        }
        List<IUser> users = group.getUsers();
        users.add(iUser);
        groupCache.put(id, group);
        return group;
    }

    /**
     * 避免用户重连
     *
     * @param user
     */
    public void userLogin(IUser user) {
        if (userCache.containsKey(user.getUsername())) {
            Channel channel = null;
            if (user.getWsChannel() != null && user.getWsChannel().isOpen()) {
                channel = user.getWsChannel();
            }
            if (user.getTcpChannel() != null && user.getTcpChannel().isOpen()) {
                channel = user.getTcpChannel();
            }
        }
        userCache.put(user.getId(), user);
    }


    public IUser getUser(String username) {
        return userCache.get(username);
    }
}
