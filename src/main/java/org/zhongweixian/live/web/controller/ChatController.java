package org.zhongweixian.live.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zhongweixian.live.web.CommonResponse;

/**
 * Created by caoliang on 2019-07-26
 * <p>
 * 所有接口统一鉴权,路径跟socket接口命名一样
 */

@RestController
@RequestMapping("chat")
public class ChatController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(ChatController.class);


    /**
     * 获取会话列表，包括好友和群组
     *
     * @return
     */
    @GetMapping("CMD_TALK_LIST")
    public CommonResponse talkList() {

        return new CommonResponse("");
    }

    /**
     * 获取好友信息
     *
     * @return
     */
    @GetMapping("CMD_USER_INFO")
    public CommonResponse userInfo() {

        return new CommonResponse("");
    }

    /**
     * 获取群组信息
     *
     * @return
     */
    @GetMapping("CMD_GROUP_INFO")
    public CommonResponse groupInfo() {

        return new CommonResponse("");
    }


}
