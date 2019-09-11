package org.zhongweixian.live.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caoliang on 2019-07-29
 */
public class IGroup {

    private Integer id;
    private String groupName;

    private List<IUser> users = new ArrayList<>();

    private Integer userSize;

    public List<IUser> getUsers() {
        return users;
    }

    public void setUsers(List<IUser> users) {
        this.users = users;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getUserSize() {
        return userSize;
    }

    public void setUserSize(Integer userSize) {
        this.userSize = userSize;
    }
}
