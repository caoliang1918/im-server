/**
 *
 */
package org.zhongweixian.live.message;

import com.alibaba.fastjson.JSONObject;
import org.zhongweixian.live.protocol.ChatType;
import org.zhongweixian.live.protocol.Command;

import java.io.Serializable;

/**
 *
 */
public class Message implements Serializable {

    private static final long serialVersionUID = -6375331164604259933L;
    /**
     * 消息创建时间
     * new Date().getTime()
     */
    protected Long createTime;
    /**
     * 消息id，全局唯一
     */
    protected String id;
    /**
     * 聊天类型
     */
    protected ChatType chatType;

    /**
     * 消息类型
     */
    protected Command command;
    /**
     * 扩展参数字段
     */
    protected JSONObject extras;

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ChatType getChatType() {
        return chatType;
    }

    public void setChatType(ChatType chatType) {
        this.chatType = chatType;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public JSONObject getExtras() {
        return extras;
    }

    public void setExtras(JSONObject extras) {
        this.extras = extras;
    }

    public String toJsonString() {
        return JSONObject.toJSONString(this);
    }

    public byte[] toByte() {
        return JSONObject.toJSONBytes(this);
    }

    public abstract static class Builder<T extends Message, B extends Message.Builder<T, B>> {
        /**
         * 消息创建时间
         */
        protected Long createTime;
        /**
         * 消息id，全局唯一
         */
        protected String id;
        /**
         * 消息cmd命令;
         */
        protected Integer cmd;
        /**
         * 扩展字段;
         */
        protected JSONObject extras;
        private B theBuilder = this.getThis();

        /**
         * 供子类获取自身builder抽象类;
         * @return
         */
        protected abstract B getThis();

        public B setCreateTime(Long createTime) {
            this.createTime = createTime;
            return theBuilder;
        }

        public B setId(String id) {
            this.id = id;
            return theBuilder;
        }

        public B setCmd(Integer cmd) {
            this.cmd = cmd;
            return theBuilder;
        }

        public B addExtra(String key, Object value) {
            if (null == value) {
                return theBuilder;
            } else {
                if (null == extras) {
                    this.extras = new JSONObject();
                }
                this.extras.put(key, value);
                return theBuilder;
            }
        }

        /**
         * 供子类实现的抽象构建对象
         * @return
         */
        public abstract T build();
    }
}
